package forSushi.sir;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class SllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sir/sll/MyLinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sir/sll/MyLinkedList.*");
		super.configureSushiScope(p, "sir/sll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sir/sir-sll.jbse"));
	}
	
	public static void main(String[] args) {
		SllLauncher launcher = new SllLauncher();
		launcher.startSushi();
	}

}
