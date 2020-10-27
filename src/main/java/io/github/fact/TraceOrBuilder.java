// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trace.proto

package io.github.fact;

public interface TraceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fact.Trace)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string ID = 1;</code>
   * @return The iD.
   */
  java.lang.String getID();
  /**
   * <code>string ID = 1;</code>
   * @return The bytes for iD.
   */
  com.google.protobuf.ByteString
      getIDBytes();

  /**
   * <code>string ChildOf = 2;</code>
   * @return The childOf.
   */
  java.lang.String getChildOf();
  /**
   * <code>string ChildOf = 2;</code>
   * @return The bytes for childOf.
   */
  com.google.protobuf.ByteString
      getChildOfBytes();

  /**
   * <code>.google.protobuf.Timestamp Timestamp = 3;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <code>.google.protobuf.Timestamp Timestamp = 3;</code>
   * @return The timestamp.
   */
  com.google.protobuf.Timestamp getTimestamp();
  /**
   * <code>.google.protobuf.Timestamp Timestamp = 3;</code>
   */
  com.google.protobuf.TimestampOrBuilder getTimestampOrBuilder();

  /**
   * <code>string ContainerID = 4;</code>
   * @return The containerID.
   */
  java.lang.String getContainerID();
  /**
   * <code>string ContainerID = 4;</code>
   * @return The bytes for containerID.
   */
  com.google.protobuf.ByteString
      getContainerIDBytes();

  /**
   * <code>string HostID = 5;</code>
   * @return The hostID.
   */
  java.lang.String getHostID();
  /**
   * <code>string HostID = 5;</code>
   * @return The bytes for hostID.
   */
  com.google.protobuf.ByteString
      getHostIDBytes();

  /**
   * <code>.google.protobuf.Timestamp BootTime = 6;</code>
   * @return Whether the bootTime field is set.
   */
  boolean hasBootTime();
  /**
   * <code>.google.protobuf.Timestamp BootTime = 6;</code>
   * @return The bootTime.
   */
  com.google.protobuf.Timestamp getBootTime();
  /**
   * <code>.google.protobuf.Timestamp BootTime = 6;</code>
   */
  com.google.protobuf.TimestampOrBuilder getBootTimeOrBuilder();

  /**
   * <code>float Cost = 7;</code>
   * @return The cost.
   */
  float getCost();

  /**
   * <code>.google.protobuf.Timestamp StartTime = 8;</code>
   * @return Whether the startTime field is set.
   */
  boolean hasStartTime();
  /**
   * <code>.google.protobuf.Timestamp StartTime = 8;</code>
   * @return The startTime.
   */
  com.google.protobuf.Timestamp getStartTime();
  /**
   * <code>.google.protobuf.Timestamp StartTime = 8;</code>
   */
  com.google.protobuf.TimestampOrBuilder getStartTimeOrBuilder();

  /**
   * <code>int32 Status = 9;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.google.protobuf.Timestamp EndTime = 10;</code>
   * @return Whether the endTime field is set.
   */
  boolean hasEndTime();
  /**
   * <code>.google.protobuf.Timestamp EndTime = 10;</code>
   * @return The endTime.
   */
  com.google.protobuf.Timestamp getEndTime();
  /**
   * <code>.google.protobuf.Timestamp EndTime = 10;</code>
   */
  com.google.protobuf.TimestampOrBuilder getEndTimeOrBuilder();

  /**
   * <code>string Platform = 11;</code>
   * @return The platform.
   */
  java.lang.String getPlatform();
  /**
   * <code>string Platform = 11;</code>
   * @return The bytes for platform.
   */
  com.google.protobuf.ByteString
      getPlatformBytes();

  /**
   * <code>string Region = 12;</code>
   * @return The region.
   */
  java.lang.String getRegion();
  /**
   * <code>string Region = 12;</code>
   * @return The bytes for region.
   */
  com.google.protobuf.ByteString
      getRegionBytes();

  /**
   * <code>string Runtime = 13;</code>
   * @return The runtime.
   */
  java.lang.String getRuntime();
  /**
   * <code>string Runtime = 13;</code>
   * @return The bytes for runtime.
   */
  com.google.protobuf.ByteString
      getRuntimeBytes();

  /**
   * <code>int32 Memory = 14;</code>
   * @return The memory.
   */
  int getMemory();

  /**
   * <code>.google.protobuf.Duration ExecutionLatency = 15;</code>
   * @return Whether the executionLatency field is set.
   */
  boolean hasExecutionLatency();
  /**
   * <code>.google.protobuf.Duration ExecutionLatency = 15;</code>
   * @return The executionLatency.
   */
  com.google.protobuf.Duration getExecutionLatency();
  /**
   * <code>.google.protobuf.Duration ExecutionLatency = 15;</code>
   */
  com.google.protobuf.DurationOrBuilder getExecutionLatencyOrBuilder();

  /**
   * <code>map&lt;string, string&gt; Env = 16;</code>
   */
  int getEnvCount();
  /**
   * <code>map&lt;string, string&gt; Env = 16;</code>
   */
  boolean containsEnv(
      java.lang.String key);
  /**
   * Use {@link #getEnvMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getEnv();
  /**
   * <code>map&lt;string, string&gt; Env = 16;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getEnvMap();
  /**
   * <code>map&lt;string, string&gt; Env = 16;</code>
   */

  java.lang.String getEnvOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; Env = 16;</code>
   */

  java.lang.String getEnvOrThrow(
      java.lang.String key);

  /**
   * <code>map&lt;string, string&gt; Tags = 17;</code>
   */
  int getTagsCount();
  /**
   * <code>map&lt;string, string&gt; Tags = 17;</code>
   */
  boolean containsTags(
      java.lang.String key);
  /**
   * Use {@link #getTagsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getTags();
  /**
   * <code>map&lt;string, string&gt; Tags = 17;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getTagsMap();
  /**
   * <code>map&lt;string, string&gt; Tags = 17;</code>
   */

  java.lang.String getTagsOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; Tags = 17;</code>
   */

  java.lang.String getTagsOrThrow(
      java.lang.String key);

  /**
   * <code>map&lt;uint64, string&gt; Logs = 18;</code>
   */
  int getLogsCount();
  /**
   * <code>map&lt;uint64, string&gt; Logs = 18;</code>
   */
  boolean containsLogs(
      long key);
  /**
   * Use {@link #getLogsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.Long, java.lang.String>
  getLogs();
  /**
   * <code>map&lt;uint64, string&gt; Logs = 18;</code>
   */
  java.util.Map<java.lang.Long, java.lang.String>
  getLogsMap();
  /**
   * <code>map&lt;uint64, string&gt; Logs = 18;</code>
   */

  java.lang.String getLogsOrDefault(
      long key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;uint64, string&gt; Logs = 18;</code>
   */

  java.lang.String getLogsOrThrow(
      long key);

  /**
   * <code>repeated string Args = 19;</code>
   * @return A list containing the args.
   */
  java.util.List<java.lang.String>
      getArgsList();
  /**
   * <code>repeated string Args = 19;</code>
   * @return The count of args.
   */
  int getArgsCount();
  /**
   * <code>repeated string Args = 19;</code>
   * @param index The index of the element to return.
   * @return The args at the given index.
   */
  java.lang.String getArgs(int index);
  /**
   * <code>repeated string Args = 19;</code>
   * @param index The index of the value to return.
   * @return The bytes of the args at the given index.
   */
  com.google.protobuf.ByteString
      getArgsBytes(int index);
}