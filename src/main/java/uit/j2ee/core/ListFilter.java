/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.j2ee.core;

import java.util.List;

/**
 *
 * @author LAP10599-local
 */

interface ListFilter<T> {

    public List<T> select(List<T> list);
}