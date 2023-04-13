package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.TitleScreenModel;
import edu.ntnu.idatt2001.views.TitleScreenView;

import java.util.ArrayList;

public class TitleScreenController {
  private final TitleScreenView view;
  private final TitleScreenModel model;

  public TitleScreenController() {
    ArrayList storyNameList = new ArrayList();
    model = new TitleScreenModel();
    view = new TitleScreenView(model, storyNameList, this::dummyAction);
  }

  private void dummyAction() {
  }
}
