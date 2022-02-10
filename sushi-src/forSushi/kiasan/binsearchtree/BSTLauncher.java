package forSushi.kiasan.binsearchtree;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class BSTLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/binsearchtree/BinarySearchTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/kiasan/binsearchtree/BinarySearchTree.*");
		super.configureScope(p, "forSushi/kiasan/binsearchtree/BinaryNode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/bst.jbse"));
	}
	
	public static void main(String[] args) {
		BSTLauncher launcher = new BSTLauncher();
		launcher.startSushi();
	}

}
