/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package argo.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Downtime extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Downtime\",\"namespace\":\"argo.avro\",\"fields\":[{\"name\":\"hostname\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"service\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"start_time\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"end_time\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String hostname;
  @Deprecated public java.lang.String service;
  @Deprecated public java.lang.String start_time;
  @Deprecated public java.lang.String end_time;

  /**
   * Default constructor.
   */
  public Downtime() {}

  /**
   * All-args constructor.
   */
  public Downtime(java.lang.String hostname, java.lang.String service, java.lang.String start_time, java.lang.String end_time) {
    this.hostname = hostname;
    this.service = service;
    this.start_time = start_time;
    this.end_time = end_time;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return hostname;
    case 1: return service;
    case 2: return start_time;
    case 3: return end_time;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: hostname = (java.lang.String)value$; break;
    case 1: service = (java.lang.String)value$; break;
    case 2: start_time = (java.lang.String)value$; break;
    case 3: end_time = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
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
   * Gets the value of the 'start_time' field.
   */
  public java.lang.String getStartTime() {
    return start_time;
  }

  /**
   * Sets the value of the 'start_time' field.
   * @param value the value to set.
   */
  public void setStartTime(java.lang.String value) {
    this.start_time = value;
  }

  /**
   * Gets the value of the 'end_time' field.
   */
  public java.lang.String getEndTime() {
    return end_time;
  }

  /**
   * Sets the value of the 'end_time' field.
   * @param value the value to set.
   */
  public void setEndTime(java.lang.String value) {
    this.end_time = value;
  }

  /** Creates a new Downtime RecordBuilder */
  public static argo.avro.Downtime.Builder newBuilder() {
    return new argo.avro.Downtime.Builder();
  }
  
  /** Creates a new Downtime RecordBuilder by copying an existing Builder */
  public static argo.avro.Downtime.Builder newBuilder(argo.avro.Downtime.Builder other) {
    return new argo.avro.Downtime.Builder(other);
  }
  
  /** Creates a new Downtime RecordBuilder by copying an existing Downtime instance */
  public static argo.avro.Downtime.Builder newBuilder(argo.avro.Downtime other) {
    return new argo.avro.Downtime.Builder(other);
  }
  
  /**
   * RecordBuilder for Downtime instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Downtime>
    implements org.apache.avro.data.RecordBuilder<Downtime> {

    private java.lang.String hostname;
    private java.lang.String service;
    private java.lang.String start_time;
    private java.lang.String end_time;

    /** Creates a new Builder */
    private Builder() {
      super(argo.avro.Downtime.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(argo.avro.Downtime.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Downtime instance */
    private Builder(argo.avro.Downtime other) {
            super(argo.avro.Downtime.SCHEMA$);
      if (isValidValue(fields()[0], other.hostname)) {
        this.hostname = data().deepCopy(fields()[0].schema(), other.hostname);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.service)) {
        this.service = data().deepCopy(fields()[1].schema(), other.service);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.start_time)) {
        this.start_time = data().deepCopy(fields()[2].schema(), other.start_time);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.end_time)) {
        this.end_time = data().deepCopy(fields()[3].schema(), other.end_time);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'hostname' field */
    public java.lang.String getHostname() {
      return hostname;
    }
    
    /** Sets the value of the 'hostname' field */
    public argo.avro.Downtime.Builder setHostname(java.lang.String value) {
      validate(fields()[0], value);
      this.hostname = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'hostname' field has been set */
    public boolean hasHostname() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'hostname' field */
    public argo.avro.Downtime.Builder clearHostname() {
      hostname = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'service' field */
    public java.lang.String getService() {
      return service;
    }
    
    /** Sets the value of the 'service' field */
    public argo.avro.Downtime.Builder setService(java.lang.String value) {
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
    public argo.avro.Downtime.Builder clearService() {
      service = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'start_time' field */
    public java.lang.String getStartTime() {
      return start_time;
    }
    
    /** Sets the value of the 'start_time' field */
    public argo.avro.Downtime.Builder setStartTime(java.lang.String value) {
      validate(fields()[2], value);
      this.start_time = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'start_time' field has been set */
    public boolean hasStartTime() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'start_time' field */
    public argo.avro.Downtime.Builder clearStartTime() {
      start_time = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'end_time' field */
    public java.lang.String getEndTime() {
      return end_time;
    }
    
    /** Sets the value of the 'end_time' field */
    public argo.avro.Downtime.Builder setEndTime(java.lang.String value) {
      validate(fields()[3], value);
      this.end_time = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'end_time' field has been set */
    public boolean hasEndTime() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'end_time' field */
    public argo.avro.Downtime.Builder clearEndTime() {
      end_time = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Downtime build() {
      try {
        Downtime record = new Downtime();
        record.hostname = fieldSetFlags()[0] ? this.hostname : (java.lang.String) defaultValue(fields()[0]);
        record.service = fieldSetFlags()[1] ? this.service : (java.lang.String) defaultValue(fields()[1]);
        record.start_time = fieldSetFlags()[2] ? this.start_time : (java.lang.String) defaultValue(fields()[2]);
        record.end_time = fieldSetFlags()[3] ? this.end_time : (java.lang.String) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
