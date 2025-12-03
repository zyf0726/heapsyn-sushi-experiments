package forSushi.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class TreeMapLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/treemap/TreeMap");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/treemap/TreeMap(?!.*HEXTriggers.*$).*");
		super.configureSushiScope(p, "sushi/treemap/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/treemap.jbse"));
	}
	
	public static void main(String[] args) {
		TreeMapLauncher launcher = new TreeMapLauncher();
		launcher.startSushi("[forSushi]sushi.TreeMap.txt");
	}

}
