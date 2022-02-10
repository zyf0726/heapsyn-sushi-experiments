package forSushi.kiasan.aatree;

import common.BasicLauncher;
import sushi.Coverage;
import sushi.Options;

import static common.Settings.*;

public class AATreeLauncher extends BasicLauncher {
	
	@Override
	public void configure(Options p) {
		super.configure(p);
		p.setTargetClass("forSushi/kiasan/aatree/AATree");
		p.setCoverage(Coverage.BRANCHES);
		super.configureScope(p, "forSushi/kiasan/aatree/AATree$AANode");
		p.setHEXFiles(SETTINGS_PATH.resolve("forSushi/kiasan/aatree.jbse"));
	}
	
	public static void main(String[] args) {
		AATreeLauncher launcher = new AATreeLauncher();
		launcher.startSushi();
	}

}
