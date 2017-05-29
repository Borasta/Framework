package odbg.org.libs;

import java.util.LinkedList;
import java.util.Properties;

/**
 * 
 * @author orlando
 *
 *	@version 1.1
 */

public class PJDBC {
	
	private int minSize = 2;
	private int maxSize = 5;
	private int actSize = 0;
	private Properties dataSource = null;
	
	private LinkedList<JDBC> pool = new LinkedList<>();
	
	private final OLogs log = OLogs.getInstance();
	
	public PJDBC( Properties dataSource ) {
		this.dataSource = dataSource;
		
		String minSizeStr = dataSource.getProperty("minSize");
		String maxSizeStr = dataSource.getProperty("maxSize");
		
		int minSize = (minSizeStr != null) ? Integer.parseInt(minSizeStr) : 0;
		int maxSize = (maxSizeStr != null) ? Integer.parseInt(maxSizeStr) : 0;
		
		if( minSize >= 1 && minSize <= maxSize ) {
			this.minSize = minSize;
			this.maxSize = maxSize;
		}
		
		this.fillPool();
	}
	
	public PJDBC(Properties dataSource, int minSize, int maxSize) {
		this.dataSource = dataSource;
		if( minSize >= 1 && minSize <= maxSize ) {
			this.minSize = minSize;
			this.maxSize = maxSize;
		}
		this.fillPool();
	}
	
	public synchronized JDBC getJDBC() {
		log.log("Se esta tratando de obtener una conexion del pool");
		JDBC jdbc = null;
		try {
			if( this.actSize >= this.maxSize ) {
				log.log("No hay conexiones disponibles, el programa se pondra en espera");
				wait();
			}
			
			if( this.actSize < this.maxSize ) {
				log.log("");
				this.actSize++;
				jdbc = this.pool.poll();
				this.fillPool();
			}
			
		} catch (InterruptedException e) {
			log.log(new StringBuilder("Hubo un problema al tratar de poner en espera el programa: ").append(e).toString());
		}
		return jdbc;
	}
	
	public synchronized void returnJDBC( JDBC jdbc ) {
		if( jdbc.compareDataSource(this.dataSource)) {
			this.actSize--;
			if( this.pool.size() < this.minSize ) {
				this.pool.add(jdbc);
			}
			else {
				jdbc.close();
			}
			notify();
		}
		else {
			throw new Error("The connection that has been returned is not the same that was given");
		}
	}

	public int freeJDBC() {
		return this.maxSize - this.actSize;
	}
	
	private void fillPool() {
		for( int i = this.pool.size(); i < this.minSize; i++ ) {
			this.pool.add( this.createJDBC() );
		}
	}
	
	private JDBC createJDBC() {
		return new JDBC(this.dataSource);
	}
}
