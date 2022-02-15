package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/redblacktree/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/redblacktree/TreeMap.*");
		super.configureSushiScope(p, "kiasan/redblacktree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/treemap.jbse"));
	}

	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi();
	}
	
}
