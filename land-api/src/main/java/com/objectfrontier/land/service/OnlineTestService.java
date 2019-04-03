/**
 * 
 */
package com.objectfrontier.land.service;

import com.objectfrontier.land.model.OnlineTest;

/**
 * @author karthik.n
 * @since v1.0
 */

public interface OnlineTestService {

    public OnlineTest create(OnlineTest onlineTest);

    public OnlineTest update(OnlineTest onlineTest);

    public void delete(long id);

    public OnlineTest read(long id);
}
