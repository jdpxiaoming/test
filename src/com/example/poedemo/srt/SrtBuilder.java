package com.example.poedemo.srt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

import com.example.poedemo.lrc.LrcRow;

public class SrtBuilder implements ISrtBuild {

	@Override
	public List<SrtRow> getSrtRows(String rawLrc) {
		// TODO Auto-generated method stub
		if (rawLrc == null || rawLrc.length() == 0) {
			return null;
		}
		StringReader reader = new StringReader(rawLrc);
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		List<SrtRow> rows = new ArrayList<SrtRow>();
		try {
			do {
				line = br.readLine();
				if (line != null && line.length() > 0) {
					List<SrtRow> lrcRows = SrtRow.createRow(line);
					if (lrcRows != null && lrcRows.size() > 0) {
						for (SrtRow row : lrcRows) {
							rows.add(row);
						}
					}
				}

			} while (line != null);
			if (rows.size() > 0) {
				// sort by time:
				Collections.sort(rows);
			}

		} catch (Exception e) {
			return null;
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			reader.close();
		}
		return rows;
	}

}
