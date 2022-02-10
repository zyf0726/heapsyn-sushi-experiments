package forSushi.sushi.avl;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/sushi/avl/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/sushi/avl/AvlTree.*");
		super.configureScope(p, "forSushi/sushi/avl/AvlNode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/sushi/avltree.jbse"));
	}
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
