package com.example.ex_intermediate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ex_intermediate.domain.BaseballTeam;
import com.example.ex_intermediate.service.BaseballTeamService;



/**
 * 野球チーム情報を表示するコントローラクラス.
 * @author suzuki
 */

@Controller
@RequestMapping("/")
public class BaseballTeamController {
  
  @Autowired
  private BaseballTeamService baseballTeamService;

  /**
   * 野球チーム一覧を発足日の昇順で取得して表示するメソッド.
   * @param model
   * @return
   */

  @GetMapping("/list")
  public String showList(Model model) {
    List<BaseballTeam> teams = baseballTeamService.showList();
    model.addAttribute("teams", teams);
    return "team-list";
  }

  /**
   * クリックされたチームの詳細情報を表示するメソッド.
   * @param id チームID
   * @param model ビューにデータを格納するModelオブジェクト
   * @return 詳細画面
   */
  @GetMapping("/team-detail")
  public String showDetail(@RequestParam("id") Integer id, Model model) {
    BaseballTeam team = baseballTeamService.showDetail(id);
    model.addAttribute("team", team);
    return "team-detail";
  }
}