/* 
 Copyright (c) 2013 LDBC
 Linked Data Benchmark Council (http://www.ldbcouncil.org)
 
 This file is part of ldbc_snb_datagen.
 
 ldbc_snb_datagen is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 ldbc_snb_datagen is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with ldbc_snb_datagen.  If not, see <http://www.gnu.org/licenses/>.
 
 Copyright (C) 2011 OpenLink Software <bdsmt@openlinksw.com>
 All Rights Reserved.
 
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation;  only Version 2 of the License dated
 June 1991.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.*/
package ldbc.snb.datagen.hadoop.writer;


import com.clearspring.analytics.util.Lists;
import com.google.common.collect.Iterables;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.util.List;

public class HdfsCsvWriter extends HdfsWriter {

    private String separator;
    private StringBuffer buffer;

    public HdfsCsvWriter(FileSystem fs, String outputDir, String prefix, int numFiles, boolean compressed, String separator) throws IOException {
        super(fs, outputDir, prefix, numFiles, compressed, "csv");
        this.separator = separator;
        this.buffer = new StringBuffer(2048);
    }

    public void writeHeader(List<String> entry) {
        buffer.setLength(0);
        for (int i = 0; i < entry.size(); ++i) {
            buffer.append(entry.get(i));
            if (i < entry.size() - 1)
                buffer.append(separator);
        }
        buffer.append("\n");
        this.writeAllPartitions(buffer.toString());
    }

    @SafeVarargs
    public final void writeHeader(List<String>... entry) {
        writeHeader(Lists.newArrayList(Iterables.concat(entry)));
    }

    public void writeEntry(List<String> entry) {
        buffer.setLength(0);
        for (int i = 0; i < entry.size(); ++i) {
            buffer.append(entry.get(i));
            if (i < entry.size() - 1)
                buffer.append(separator);
        }
        buffer.append("\n");
        this.write(buffer.toString());
    }

    @SafeVarargs
    public final void writeEntry(List<String>... entry) {
        writeEntry(Lists.newArrayList(Iterables.concat(entry)));
    }

}
