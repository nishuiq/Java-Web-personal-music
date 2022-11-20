package com.xxx.dao;

import com.xxx.bean.Music;

import java.util.List;

public interface IMusicDao {
    // 保存音乐数据
    public int save(Music music);

    // 根据wid删除
    public int delete(int id, int wid);

    // 修改用户的music
    public int update(Music music);

    // 查找某用户下所有音乐信息
    public List<Music> findAll(int id);
}
