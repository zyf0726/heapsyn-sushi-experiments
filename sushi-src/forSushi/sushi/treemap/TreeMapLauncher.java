package forSushi.sushi.treemap;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/sushi/treemap/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/sushi/treemap/TreeMap(?!.*HEXTriggers.*$).*");
		super.configureScope(p, "forSushi/sushi/treemap/TreeMap$Entry");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/sushi/treemap.jbse"));
	}
	
	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi();
	}

}
