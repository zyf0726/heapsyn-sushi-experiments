package forSushi.sushi;

import static common.Settings.*;

import common.BasicLauncher;
import sushi.Coverage;

public class DllLauncher extends BasicLauncher {
	
	@Override
	public void configureSushi(sushi.Options p) {
		super.configureSushi(p);
		p.setTargetClass("sushi/dll/LinkedList");
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("sushi/dll/LinkedList.*");
		super.configureSushiScope(p, "sushi/dll/");
		p.setHEXFiles(SETTINGS_PATH.resolve("sushi/dll.jbse"));
	}
	
	public static void main(String[] args) {
		DllLauncher launcher = new DllLauncher();
		launcher.startSushi("[forSushi]sushi.dll.txt");
	}

}
