package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class AvlLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/avltree/AvlTree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/avltree/AvlTree.*");
		super.configureSushiScope(p, "kiasan/avltree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/avltree.jbse"));
	}
	
	public static void main(String[] args) {
		AvlLauncher launcher = new AvlLauncher();
		launcher.startSushi();
	}

}
