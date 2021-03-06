/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package argo.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class MetricDataOld extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MetricData\",\"namespace\":\"argo.avro\",\"fields\":[{\"name\":\"timestamp\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"service\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"hostname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"metric\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"status\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"monitoring_host\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"summary\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"message\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"tags\",\"type\":[\"null\",{\"type\":\"map\",\"values\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"avro.java.string\":\"String\"}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String timestamp;
  @Deprecated public java.lang.String service;
  @Deprecated public java.lang.String hostname;
  @Deprecated public java.lang.String metric;
  @Deprecated public java.lang.String status;
  @Deprecated public java.lang.String monitoring_host;
  @Deprecated public java.lang.String summary;
  @Deprecated public java.lang.String message;
  @Deprecated public java.util.Map<java.lang.String,java.lang.String> tags;

  /**
   * Default constructor.
   */
  public MetricDataOld() {}

  /**
   * All-args constructor.
   */
  public MetricDataOld(java.lang.String timestamp, java.lang.String service, java.lang.String hostname, java.lang.String metric, java.lang.String status, java.lang.String monitoring_host, java.lang.String summary, java.lang.String message, java.util.Map<java.lang.String,java.lang.String> tags) {
    this.timestamp = timestamp;
    this.service = service;
    this.hostname = hostname;
    this.metric = metric;
    this.status = status;
    this.monitoring_host = monitoring_host;
    this.summary = summary;
    this.message = message;
    this.tags = tags;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return timestamp;
    case 1: return service;
    case 2: return hostname;
    case 3: return metric;
    case 4: return status;
    case 5: return monitoring_host;
    case 6: return summary;
    case 7: return message;
    case 8: return tags;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: timestamp = (java.lang.String)value$; break;
    case 1: service = (java.lang.String)value$; break;
    case 2: hostname = (java.lang.String)value$; break;
    case 3: metric = (java.lang.String)value$; break;
    case 4: status = (java.lang.String)value$; break;
    case 5: monitoring_host = (java.lang.String)value$; break;
    case 6: summary = (java.lang.String)value$; break;
    case 7: message = (java.lang.String)value$; break;
    case 8: tags = (java.util.Map<java.lang.String,java.lang.String>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'timestamp' field.
   */
  public java.lang.String getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.String value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'service' field.
   */
  public java.lang.String getService() {
    return service;
  }

  /**
   * Sets the value of the 'service' field.
   * @param value the value to set.
   */
  public void setService(java.lang.String value) {
    this.service = value;
  }

  /**
   * Gets the value of the 'hostname' field.
   */
  public java.lang.String getHostname() {
    return hostname;
  }

  /**
   * Sets the value of the 'hostname' field.
   * @param value the value to set.
   */
  public void setHostname(java.lang.String value) {
    this.hostname = value;
  }

  /**
   * Gets the value of the 'metric' field.
   */
  public java.lang.String getMetric() {
    return metric;
  }

  /**
   * Sets the value of the 'metric' field.
   * @param value the value to set.
   */
  public void setMetric(java.lang.String value) {
    this.metric = value;
  }

  /**
   * Gets the value of the 'status' field.
   */
  public java.lang.String getStatus() {
    return status;
  }

  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.String value) {
    this.status = value;
  }

  /**
   * Gets the value of the 'monitoring_host' field.
   */
  public java.lang.String getMonitoringHost() {
    return monitoring_host;
  }

  /**
   * Sets the value of the 'monitoring_host' field.
   * @param value the value to set.
   */
  public void setMonitoringHost(java.lang.String value) {
    this.monitoring_host = value;
  }

  /**
   * Gets the value of the 'summary' field.
   */
  public java.lang.String getSummary() {
    return summary;
  }

  /**
   * Sets the value of the 'summary' field.
   * @param value the value to set.
   */
  public void setSummary(java.lang.String value) {
    this.summary = value;
  }

  /**
   * Gets the value of the 'message' field.
   */
  public java.lang.String getMessage() {
    return message;
  }

  /**
   * Sets the value of the 'message' field.
   * @param value the value to set.
   */
  public void setMessage(java.lang.String value) {
    this.message = value;
  }

  /**
   * Gets the value of the 'tags' field.
   */
  public java.util.Map<java.lang.String,java.lang.String> getTags() {
    return tags;
  }

  /**
   * Sets the value of the 'tags' field.
   * @param value the value to set.
   */
  public void setTags(java.util.Map<java.lang.String,java.lang.String> value) {
    this.tags = value;
  }

  /** Creates a new MetricData RecordBuilder */
  public static argo.avro.MetricDataOld.Builder newBuilder() {
    return new argo.avro.MetricDataOld.Builder();
  }
  
  /** Creates a new MetricData RecordBuilder by copying an existing Builder */
  public static argo.avro.MetricDataOld.Builder newBuilder(argo.avro.MetricDataOld.Builder other) {
    return new argo.avro.MetricDataOld.Builder(other);
  }
  
  /** Creates a new MetricData RecordBuilder by copying an existing MetricData instance */
  public static argo.avro.MetricDataOld.Builder newBuilder(argo.avro.MetricDataOld other) {
    return new argo.avro.MetricDataOld.Builder(other);
  }
  
  /**
   * RecordBuilder for MetricData instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MetricDataOld>
    implements org.apache.avro.data.RecordBuilder<MetricDataOld> {

    private java.lang.String timestamp;
    private java.lang.String service;
    private java.lang.String hostname;
    private java.lang.String metric;
    private java.lang.String status;
    private java.lang.String monitoring_host;
    private java.lang.String summary;
    private java.lang.String message;
    private java.util.Map<java.lang.String,java.lang.String> tags;

    /** Creates a new Builder */
    private Builder() {
      super(argo.avro.MetricDataOld.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(argo.avro.MetricDataOld.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing MetricData instance */
    private Builder(argo.avro.MetricDataOld other) {
            super(argo.avro.MetricDataOld.SCHEMA$);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.service)) {
        this.service = data().deepCopy(fields()[1].schema(), other.service);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.hostname)) {
        this.hostname = data().deepCopy(fields()[2].schema(), other.hostname);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.metric)) {
        this.metric = data().deepCopy(fields()[3].schema(), other.metric);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.status)) {
        this.status = data().deepCopy(fields()[4].schema(), other.status);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.monitoring_host)) {
        this.monitoring_host = data().deepCopy(fields()[5].schema(), other.monitoring_host);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.summary)) {
        this.summary = data().deepCopy(fields()[6].schema(), other.summary);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.message)) {
        this.message = data().deepCopy(fields()[7].schema(), other.message);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.tags)) {
        this.tags = data().deepCopy(fields()[8].schema(), other.tags);
        fieldSetFlags()[8] = true;
      }
    }

    /** Gets the value of the 'timestamp' field */
    public java.lang.String getTimestamp() {
      return timestamp;
    }
    
    /** Sets the value of the 'timestamp' field */
    public argo.avro.MetricDataOld.Builder setTimestamp(java.lang.String value) {
      validate(fields()[0], value);
      this.timestamp = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'timestamp' field has been set */
    public boolean hasTimestamp() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'timestamp' field */
    public argo.avro.MetricDataOld.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'service' field */
    public java.lang.String getService() {
      return service;
    }
    
    /** Sets the value of the 'service' field */
    public argo.avro.MetricDataOld.Builder setService(java.lang.String value) {
      validate(fields()[1], value);
      this.service = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'service' field has been set */
    public boolean hasService() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'service' field */
    public argo.avro.MetricDataOld.Builder clearService() {
      service = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'hostname' field */
    public java.lang.String getHostname() {
      return hostname;
    }
    
    /** Sets the value of the 'hostname' field */
    public argo.avro.MetricDataOld.Builder setHostname(java.lang.String value) {
      validate(fields()[2], value);
      this.hostname = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'hostname' field has been set */
    public boolean hasHostname() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'hostname' field */
    public argo.avro.MetricDataOld.Builder clearHostname() {
      hostname = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'metric' field */
    public java.lang.String getMetric() {
      return metric;
    }
    
    /** Sets the value of the 'metric' field */
    public argo.avro.MetricDataOld.Builder setMetric(java.lang.String value) {
      validate(fields()[3], value);
      this.metric = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'metric' field has been set */
    public boolean hasMetric() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'metric' field */
    public argo.avro.MetricDataOld.Builder clearMetric() {
      metric = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'status' field */
    public java.lang.String getStatus() {
      return status;
    }
    
    /** Sets the value of the 'status' field */
    public argo.avro.MetricDataOld.Builder setStatus(java.lang.String value) {
      validate(fields()[4], value);
      this.status = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'status' field has been set */
    public boolean hasStatus() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'status' field */
    public argo.avro.MetricDataOld.Builder clearStatus() {
      status = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'monitoring_host' field */
    public java.lang.String getMonitoringHost() {
      return monitoring_host;
    }
    
    /** Sets the value of the 'monitoring_host' field */
    public argo.avro.MetricDataOld.Builder setMonitoringHost(java.lang.String value) {
      validate(fields()[5], value);
      this.monitoring_host = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'monitoring_host' field has been set */
    public boolean hasMonitoringHost() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'monitoring_host' field */
    public argo.avro.MetricDataOld.Builder clearMonitoringHost() {
      monitoring_host = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'summary' field */
    public java.lang.String getSummary() {
      return summary;
    }
    
    /** Sets the value of the 'summary' field */
    public argo.avro.MetricDataOld.Builder setSummary(java.lang.String value) {
      validate(fields()[6], value);
      this.summary = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'summary' field has been set */
    public boolean hasSummary() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'summary' field */
    public argo.avro.MetricDataOld.Builder clearSummary() {
      summary = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'message' field */
    public java.lang.String getMessage() {
      return message;
    }
    
    /** Sets the value of the 'message' field */
    public argo.avro.MetricDataOld.Builder setMessage(java.lang.String value) {
      validate(fields()[7], value);
      this.message = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'message' field has been set */
    public boolean hasMessage() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'message' field */
    public argo.avro.MetricDataOld.Builder clearMessage() {
      message = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'tags' field */
    public java.util.Map<java.lang.String,java.lang.String> getTags() {
      return tags;
    }
    
    /** Sets the value of the 'tags' field */
    public argo.avro.MetricDataOld.Builder setTags(java.util.Map<java.lang.String,java.lang.String> value) {
      validate(fields()[8], value);
      this.tags = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'tags' field has been set */
    public boolean hasTags() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'tags' field */
    public argo.avro.MetricDataOld.Builder clearTags() {
      tags = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    public MetricDataOld build() {
      try {
        MetricDataOld record = new MetricDataOld();
        record.timestamp = fieldSetFlags()[0] ? this.timestamp : (java.lang.String) defaultValue(fields()[0]);
        record.service = fieldSetFlags()[1] ? this.service : (java.lang.String) defaultValue(fields()[1]);
        record.hostname = fieldSetFlags()[2] ? this.hostname : (java.lang.String) defaultValue(fields()[2]);
        record.metric = fieldSetFlags()[3] ? this.metric : (java.lang.String) defaultValue(fields()[3]);
        record.status = fieldSetFlags()[4] ? this.status : (java.lang.String) defaultValue(fields()[4]);
        record.monitoring_host = fieldSetFlags()[5] ? this.monitoring_host : (java.lang.String) defaultValue(fields()[5]);
        record.summary = fieldSetFlags()[6] ? this.summary : (java.lang.String) defaultValue(fields()[6]);
        record.message = fieldSetFlags()[7] ? this.message : (java.lang.String) defaultValue(fields()[7]);
        record.tags = fieldSetFlags()[8] ? this.tags : (java.util.Map<java.lang.String,java.lang.String>) defaultValue(fields()[8]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
