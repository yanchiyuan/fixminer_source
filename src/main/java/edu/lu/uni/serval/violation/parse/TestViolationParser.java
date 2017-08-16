package edu.lu.uni.serval.violation.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.lu.uni.serval.config.Configuration;
import edu.lu.uni.serval.utils.FileHelper;

public class TestViolationParser {

	private static final String REPO_PATH = "/Volumes/MacBook/repos/";
	
	public static void main(String[] args) {
		List<File> repositoriesList = new ArrayList<>();
		File repositories = new File(REPO_PATH);
		File[] subFiles = repositories.listFiles();
		for (File subFile : subFiles) {
			if (subFile.isDirectory()) {
				File[] repos = subFile.listFiles();
				for (File repo : repos) {
					if (repo.isDirectory()) {
						repositoriesList.add(repo);
					}
				}
			}
		}
		String fixedAlarmFile = "Dataset/fixed-alarms-v0.3.list.txt";

		/**
		 * 544 projects.
		 * 84103 alarms.
		 * 
		 * cd ../../7/buggy/
		 * mvn compile
		 * mvn package -Dmaven.test.skip=true
		 * 
		 * Lang 54, 56-65
		 * Math 105 and 106
		 */

		final String previousFilesPath = Configuration.GUM_TREE_INPUT + "prevFiles/";
		final String revisedFilesPath = Configuration.GUM_TREE_INPUT + "revFiles/";
		final String positionsFilePath = Configuration.GUM_TREE_INPUT + "positions/";
		final String diffentryFilePath = Configuration.GUM_TREE_INPUT + "diffentries/";
		FileHelper.createDirectory(previousFilesPath);
		FileHelper.createDirectory(revisedFilesPath);
		FileHelper.createDirectory(positionsFilePath);
		FileHelper.createDirectory(diffentryFilePath);
		
		ViolationParser parser = new ViolationParser();
		parser.parseViolations(fixedAlarmFile, repositoriesList, previousFilesPath, revisedFilesPath, positionsFilePath, diffentryFilePath);
	}

}
