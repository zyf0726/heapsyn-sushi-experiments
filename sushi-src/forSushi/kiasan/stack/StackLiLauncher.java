package forSushi.kiasan.stack;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class StackLiLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/stack/StackLi");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("forSushi/kiasan/stack/StackLi.*");
		super.configureScope(p, "forSushi/kiasan/stack/ListNode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/stackli.jbse"));
	}
	
	public static void main(String[] args) {
		StackLiLauncher launcher = new StackLiLauncher();
		launcher.startSushi();
	}

}
