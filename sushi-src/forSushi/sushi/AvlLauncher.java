package forSushi.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/avl/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/avl/AvlTree.*");
		super.configureSushiScope(p, "sushi/avl/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/avltree.jbse"));
	}
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
