package com.xxx.dao.impl;

import com.xxx.bean.Music;
import com.xxx.dao.IMusicDao;
import com.xxx.util.DbcpPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DbUtilsMusicDaoImpl implements IMusicDao {
    // 创建QueryRunner对象
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public int save(Music music) {
        String sql = "INSERT INTO tb_music (title,time," +
                "author,album_name" +
                ",id,wid) " +
                "VALUES (?,?,?,?,?,?)";
        Object params[] = { music.getTitle(), music.getTime(),
                music.getAuthor(), music.getAlbum_name(), music.getId(),
                music.getWid()};
        int result = 0;
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int id, int wid) {
        String sql = "DELETE FROM tb_music WHERE id = ? and wid = ?";
        int result = 0;
        Object params[] = {id, wid};
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Music music) {
        String sql = "UPDATE tb_music SET title = ?,time = ?, author = ?, album_name = ? WHERE wid = ? and id = ?";
        Object params[] = { music.getTitle(), music.getTime(),
                music.getAuthor(), music.getAlbum_name(),
                music.getWid(), music.getId()};
        int result = 0;
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Music> findAll(int id) {
        String sql = "SELECT * FROM tb_music WHERE id = '" + id + "'";
        List<Music> list = null;
        try {
            list = queryRunner.query(DbcpPool.getConnection(), sql,
                    new BeanListHandler<Music>(Music.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
