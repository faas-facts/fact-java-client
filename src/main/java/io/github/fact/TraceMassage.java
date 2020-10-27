// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: trace.proto

package io.github.fact;

public final class TraceMassage {
  private TraceMassage() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fact_Trace_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fact_Trace_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fact_Trace_EnvEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fact_Trace_EnvEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fact_Trace_TagsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fact_Trace_TagsEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fact_Trace_LogsEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fact_Trace_LogsEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013trace.proto\022\004fact\032\037google/protobuf/tim" +
      "estamp.proto\032\036google/protobuf/duration.p" +
      "roto\"\231\005\n\005Trace\022\n\n\002ID\030\001 \001(\t\022\017\n\007ChildOf\030\002 " +
      "\001(\t\022-\n\tTimestamp\030\003 \001(\0132\032.google.protobuf" +
      ".Timestamp\022\023\n\013ContainerID\030\004 \001(\t\022\016\n\006HostI" +
      "D\030\005 \001(\t\022,\n\010BootTime\030\006 \001(\0132\032.google.proto" +
      "buf.Timestamp\022\014\n\004Cost\030\007 \001(\002\022-\n\tStartTime" +
      "\030\010 \001(\0132\032.google.protobuf.Timestamp\022\016\n\006St" +
      "atus\030\t \001(\005\022+\n\007EndTime\030\n \001(\0132\032.google.pro" +
      "tobuf.Timestamp\022\020\n\010Platform\030\013 \001(\t\022\016\n\006Reg" +
      "ion\030\014 \001(\t\022\017\n\007Runtime\030\r \001(\t\022\016\n\006Memory\030\016 \001" +
      "(\005\0223\n\020ExecutionLatency\030\017 \001(\0132\031.google.pr" +
      "otobuf.Duration\022!\n\003Env\030\020 \003(\0132\024.fact.Trac" +
      "e.EnvEntry\022#\n\004Tags\030\021 \003(\0132\025.fact.Trace.Ta" +
      "gsEntry\022#\n\004Logs\030\022 \003(\0132\025.fact.Trace.LogsE" +
      "ntry\022\014\n\004Args\030\023 \003(\t\032*\n\010EnvEntry\022\013\n\003key\030\001 " +
      "\001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\032+\n\tTagsEntry\022\013\n\003k" +
      "ey\030\001 \001(\t\022\r\n\005value\030\002 \001(\t:\0028\001\032+\n\tLogsEntry" +
      "\022\013\n\003key\030\001 \001(\004\022\r\n\005value\030\002 \001(\t:\0028\001B\"\n\016io.g" +
      "ithub.factB\014TraceMassageH\003P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
          com.google.protobuf.DurationProto.getDescriptor(),
        });
    internal_static_fact_Trace_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_fact_Trace_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fact_Trace_descriptor,
        new java.lang.String[] { "ID", "ChildOf", "Timestamp", "ContainerID", "HostID", "BootTime", "Cost", "StartTime", "Status", "EndTime", "Platform", "Region", "Runtime", "Memory", "ExecutionLatency", "Env", "Tags", "Logs", "Args", });
    internal_static_fact_Trace_EnvEntry_descriptor =
      internal_static_fact_Trace_descriptor.getNestedTypes().get(0);
    internal_static_fact_Trace_EnvEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fact_Trace_EnvEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_fact_Trace_TagsEntry_descriptor =
      internal_static_fact_Trace_descriptor.getNestedTypes().get(1);
    internal_static_fact_Trace_TagsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fact_Trace_TagsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    internal_static_fact_Trace_LogsEntry_descriptor =
      internal_static_fact_Trace_descriptor.getNestedTypes().get(2);
    internal_static_fact_Trace_LogsEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fact_Trace_LogsEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.DurationProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
