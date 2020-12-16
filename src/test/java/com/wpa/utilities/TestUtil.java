package com.wpa.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.wpa.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));

	}

	public static void zip(String filepath) {

		try

		{

			File inFolder = new File(filepath);

			File outFolder = new File("Reports.zip");

			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));

			BufferedInputStream in = null;

			byte[] data = new byte[1000];

			String files[] = inFolder.list();

			for (int i = 0; i < files.length; i++)

			{

				in = new BufferedInputStream(new FileInputStream

				(inFolder.getPath() + "/" + files[i]), 1000);

				out.putNextEntry(new ZipEntry(files[i]));

				int count;

				while ((count = in.read(data, 0, 1000)) != -1)

				{

					out.write(data, 0, count);

				}

				out.closeEntry();

			}

			out.flush();

			out.close();

		}

		catch (Exception e)

		{

			e.printStackTrace();

		}

	}

}
