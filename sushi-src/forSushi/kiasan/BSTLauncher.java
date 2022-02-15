package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class BSTLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/binsearchtree/BinarySearchTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/binsearchtree/BinarySearchTree.*");
		super.configureSushiScope(p, "kiasan/binsearchtree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/bst.jbse"));
	}
	
	public static void main(String[] args) {
		BSTLauncher launcher = new BSTLauncher();
		launcher.startSushi();
	}

}
