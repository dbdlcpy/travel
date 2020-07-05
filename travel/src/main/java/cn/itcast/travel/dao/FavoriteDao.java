package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {

    /**
     * Get the info of Users' favorite Routes
     * @param rid : Route id
     * @param uid: User id
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * Get the times that user add routes into Favorite 
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * Add to favorite
     * @param i
     * @param uid
     */
    void add(int i, int uid);
}
