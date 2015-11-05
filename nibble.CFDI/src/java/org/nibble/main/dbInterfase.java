package org.nibble.main;

import java.sql.Connection;

public interface dbInterfase {
void create() throws java.sql.SQLException, Exception;
boolean load() throws java.sql.SQLException, java.lang.Exception;
void remove() throws java.sql.SQLException, java.lang.Exception;
void setConnection(Connection conn);
void store() throws  java.sql.SQLException, java.lang.Exception;
}
