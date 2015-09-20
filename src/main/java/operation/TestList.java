package operation;

import model.List;
import model.Node;

/**
 * Created by iushakova on 30/08/15.
 */
public class TestList {

    public static void main(String[] args) {

        List firstList = new List();
        firstList.add("1");
        firstList.add("2");
        firstList.add("3");
        firstList.printList();



        firstList.remove("3");
        firstList.printList();
        firstList.add("2");
        firstList.printList();
        firstList.remove("1");
        firstList.printList();
        firstList.remove("2");
        firstList.printList();
        firstList.add("3");
        firstList.printList();

    }

}
