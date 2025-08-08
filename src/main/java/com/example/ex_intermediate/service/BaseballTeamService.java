package com.example.ex_intermediate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex_intermediate.domain.BaseballTeam;
import com.example.ex_intermediate.repository.BaseballTeamRepository;

/**
 * チーム情報の業務処理をするサービスクラス
 * @author suzuki
 */

@Service
public class BaseballTeamService {

  @Autowired
  private BaseballTeamRepository baseballTeamRepository;

  /**
   * すべての野球チーム一覧を発足日順で取得.
   * 
   * @return すべてのチームのリスト（発足日順）
   */
  public List<BaseballTeam> showList() {
    return baseballTeamRepository.findAllOrderByInaugurationAsc();
  }

  /**
   * 指定されたIDのチーム詳細情報を取得する.
   * @param id チームID
   * @return チーム詳細情報
   */
  public BaseballTeam showDetail(Integer id) {
    BaseballTeam team = baseballTeamRepository.findById(id);
    return team;
  }
}
