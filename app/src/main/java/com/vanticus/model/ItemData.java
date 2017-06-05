package com.vanticus.model;

import java.util.ArrayList;

public class ItemData {

  public static String[] itemNameArray = {"Ganesh", "shiva", "pooja","krishna", "poojaitems", "murugan", "saraswati", "hanuman",  "temple", "fest", "more"};
  public static String[] names = {"నిత్య పారాయణ శ్లోకాలు","జీవితంలో ముక్యమైన ఘటాలు","పూజా విధి","భక్తి సుధ","పూజా సామాగ్రి","అష్టోత్తరాలు","వ్రతములు","స్తోత్రములు","పుణ్యక్షేత్రాలు","పండుగలు","మరికొన్ని"};

  public static ArrayList<Item> itemList() {
    ArrayList<Item> list = new ArrayList<>();
    for (int i = 0; i < itemNameArray.length; i++) {
      Item item = new Item();
      item.name = names[i];
      item.imageName = itemNameArray[i].replaceAll("\\s+", "").toLowerCase();
      if (i == 2 || i == 5) {
        item.isFav = true;
      }
      list.add(item);
    }
    return (list);
  }
}
