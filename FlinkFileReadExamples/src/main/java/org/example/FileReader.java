package org.example;

import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.hadoop.conf.Configuration;

import org.apache.flink.core.fs.Path;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.FileSourceSplit;

import org.apache.flink.formats.parquet.ParquetColumnarRowInputFormat;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.apache.flink.table.types.logical.RowType;
import org.apache.flink.table.types.logical.LogicalType;
import org.apache.flink.table.types.logical.VarCharType;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.runtime.typeutils.InternalTypeInfo;

public class FileReader
{
    public static void readParquet(StreamExecutionEnvironment env) {
        final LogicalType[] fieldTypes =
                new LogicalType[] {
                        new VarCharType(), new VarCharType()
                };

        final RowType rowType = RowType.of(fieldTypes, new String[] {"name", "city"});

        final ParquetColumnarRowInputFormat<FileSourceSplit> format =
                new ParquetColumnarRowInputFormat<>(
                        new Configuration(),
                        rowType,
                        InternalTypeInfo.of(rowType),
                        10,
                        false,
                        true);

        final FileSource<RowData> source =
                FileSource.forBulkFileFormat(
                        format,
                        new Path("file:///tmp/business/926641c2-0d4e-4692-a89c-65854e4567e1.parquet")
                ).build();

        final DataStream<RowData> stream =
                env.fromSource(source, WatermarkStrategy.noWatermarks(), "parquet-source");

        stream.print();
    }

    public static void readText(StreamExecutionEnvironment env) {
        final FileSource<String> source =
            FileSource.forRecordStreamFormat(new TextLineInputFormat(),
                            new Path("file:///tmp/test.txt"))
                    .build();

        final DataStream<String> stream =
                env.fromSource(source, WatermarkStrategy.noWatermarks(), "file-source");

        stream.print();
    }

    public static void main( String[] args ) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Decide which format to read
        readParquet(env);
        //readText(env);

        env.execute("File Read Example");
    }
}
