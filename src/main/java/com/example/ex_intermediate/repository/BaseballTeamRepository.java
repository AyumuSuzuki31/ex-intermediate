package com.example.ex_intermediate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.ex_intermediate.domain.BaseballTeam;

/**
 * チーム情報を操作するリポジトリクラス
 * @author suzuki
 */
@Repository
public class BaseballTeamRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * BaseballTeamのRowMapper
     */
    private static final RowMapper<BaseballTeam> BASEBALL_TEAM_ROW_MAPPER = new RowMapper<BaseballTeam>() {
        @Override
        public BaseballTeam mapRow(ResultSet rs, int rowNum) throws SQLException {
            BaseballTeam team = new BaseballTeam();
            team.setId(rs.getInt("id"));
            team.setLeagueName(rs.getString("league_name"));
            team.setTeamName(rs.getString("team_name"));
            team.setHeadquarters(rs.getString("headquarters"));
            team.setInauguration(rs.getString("inauguration"));
            team.setHistory(rs.getString("history"));
            return team;
        }
    };

    /**
     * チーム一覧を発足日の昇順で取得
     * @return チーム情報の発足日順のリスト
     */
    public List<BaseballTeam> findAllOrderByInaugurationAsc() {
        String sql = "SELECT id, league_name, team_name, headquarters, inauguration, history " +
                     "FROM teams " +
                     "ORDER BY inauguration ASC";

        return template.query(sql, BASEBALL_TEAM_ROW_MAPPER);
    }

    /**
     * 指定されたIDでチーム情報を取得
     * @param id チームのID
     * @return チームの情報
     */
    public BaseballTeam findById(Integer id) {
        String sql = "SELECT id, league_name, team_name, headquarters, inauguration, history " +
                     "FROM teams " +
                     "WHERE id = :id";

        Map<String, Object> param = Map.of("id", id);

        return template.queryForObject(sql, param, BASEBALL_TEAM_ROW_MAPPER);
    }
}
