package org.codelife.app.killer.security.entity;

/**
 * @author csl
 * @create 08/03/2017 15:53
 * @desc
 *  authority: read,write
 *      write: ADD,DELETE,UPDATE operation
 *      read: SEARCH
 *      none: deny all operations to user
 **/
public enum  Authority {
    ADD,DELETE,UPDATE,SEARCH,NONE
}
