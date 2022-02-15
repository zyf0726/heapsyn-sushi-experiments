package forSushi.kiasan;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class StackLiLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("kiasan/stack/StackLi");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("kiasan/stack/StackLi.*");
		super.configureSushiScope(p, "kiasan/stack/");
		p.setHEXFiles(SETTINGS_PATH.resolve("kiasan/stackli.jbse"));
	}
	
	public static void main(String[] args) {
		StackLiLauncher launcher = new StackLiLauncher();
		launcher.startSushi();
	}

}
