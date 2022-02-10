package forSushi.kiasan.redblacktree;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/redblacktree/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/kiasan/redblacktree/TreeMap.*");
		super.configureScope(p, "forSushi/kiasan/redblacktree/TreeMap$Entry");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/treemap.jbse"));
	}

	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi();
	}
	
}
