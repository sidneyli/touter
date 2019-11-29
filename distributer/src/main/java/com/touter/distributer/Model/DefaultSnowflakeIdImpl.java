package com.touter.distributer.Model;

import com.touter.distributer.Config.SnowflakeIdConfiguration;

/** Represents a default snowflake id implementation. */
public class DefaultSnowflakeIdImpl implements SnowflakeId {

  private int nodeId;
  private long timestamp;
  private int sequenceId;

  /**
   * Constructs the default snowflake id.
   *
   * @param nodeId Node id.
   * @param timestamp Timestamp.
   * @param sequenceId Sequence id.
   */
  public DefaultSnowflakeIdImpl(int nodeId, long timestamp, int sequenceId) {
    this.nodeId = nodeId;
    this.timestamp = timestamp;
    this.sequenceId = sequenceId;
  }

  /**
   * Converts the snowflake id into long for storage.
   *
   * @return Long typed id.
   */
  @Override
  public long toLong() {
    return this.timestamp
            << (SnowflakeIdConfiguration.TOTAL_BITS - SnowflakeIdConfiguration.EPOCH_BITS)
        | this.nodeId
            << (SnowflakeIdConfiguration.TOTAL_BITS
                - SnowflakeIdConfiguration.EPOCH_BITS
                - SnowflakeIdConfiguration.NODE_ID_BITS)
        | this.sequenceId;
  }
}
