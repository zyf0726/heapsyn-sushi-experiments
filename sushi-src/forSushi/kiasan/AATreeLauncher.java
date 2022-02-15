package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class AATreeLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/aatree/AATree");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/aatree/AATree.*");
		super.configureSushiScope(p, "kiasan/aatree/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/aatree.jbse"));
	}
	
	public static void main(String[] args) {
		AATreeLauncher launcher = new AATreeLauncher();
		launcher.startSushi();
	}

}
