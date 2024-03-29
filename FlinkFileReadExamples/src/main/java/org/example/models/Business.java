package org.example.models;

/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Business extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Business\",\"namespace\":\"org.example.models\",\"fields\":[{\"name\":\"businessId\",\"type\":\"int\"},{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"address\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"postalCode\",\"type\":\"string\"},{\"name\":\"latitude\",\"type\":\"float\"},{\"name\":\"longitude\",\"type\":\"float\"},{\"name\":\"phone\",\"type\":\"string\"},{\"name\":\"taxCode\",\"type\":\"string\"},{\"name\":\"businessCertificate\",\"type\":\"string\"},{\"name\":\"applicationDate\",\"type\":\"string\"},{\"name\":\"ownerName\",\"type\":\"string\"},{\"name\":\"ownerAddress\",\"type\":\"string\"},{\"name\":\"ownerCity\",\"type\":\"string\"},{\"name\":\"ownerState\",\"type\":\"string\"},{\"name\":\"ownerZip\",\"type\":\"string\"}]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
    @Deprecated public int businessId;
    @Deprecated public java.lang.CharSequence name;
    @Deprecated public java.lang.CharSequence address;
    @Deprecated public java.lang.CharSequence city;
    @Deprecated public java.lang.CharSequence postalCode;
    @Deprecated public float latitude;
    @Deprecated public float longitude;
    @Deprecated public java.lang.CharSequence phone;
    @Deprecated public java.lang.CharSequence taxCode;
    @Deprecated public java.lang.CharSequence businessCertificate;
    @Deprecated public java.lang.CharSequence applicationDate;
    @Deprecated public java.lang.CharSequence ownerName;
    @Deprecated public java.lang.CharSequence ownerAddress;
    @Deprecated public java.lang.CharSequence ownerCity;
    @Deprecated public java.lang.CharSequence ownerState;
    @Deprecated public java.lang.CharSequence ownerZip;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public Business() {}

    /**
     * All-args constructor.
     */
    public Business(java.lang.Integer businessId, java.lang.CharSequence name, java.lang.CharSequence address, java.lang.CharSequence city, java.lang.CharSequence postalCode, java.lang.Float latitude, java.lang.Float longitude, java.lang.CharSequence phone, java.lang.CharSequence taxCode, java.lang.CharSequence businessCertificate, java.lang.CharSequence applicationDate, java.lang.CharSequence ownerName, java.lang.CharSequence ownerAddress, java.lang.CharSequence ownerCity, java.lang.CharSequence ownerState, java.lang.CharSequence ownerZip) {
        this.businessId = businessId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.taxCode = taxCode;
        this.businessCertificate = businessCertificate;
        this.applicationDate = applicationDate;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerCity = ownerCity;
        this.ownerState = ownerState;
        this.ownerZip = ownerZip;
    }

    public org.apache.avro.Schema getSchema() { return SCHEMA$; }
    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0: return businessId;
            case 1: return name;
            case 2: return address;
            case 3: return city;
            case 4: return postalCode;
            case 5: return latitude;
            case 6: return longitude;
            case 7: return phone;
            case 8: return taxCode;
            case 9: return businessCertificate;
            case 10: return applicationDate;
            case 11: return ownerName;
            case 12: return ownerAddress;
            case 13: return ownerCity;
            case 14: return ownerState;
            case 15: return ownerZip;
            default: throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }
    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0: businessId = (java.lang.Integer)value$; break;
            case 1: name = (java.lang.CharSequence)value$; break;
            case 2: address = (java.lang.CharSequence)value$; break;
            case 3: city = (java.lang.CharSequence)value$; break;
            case 4: postalCode = (java.lang.CharSequence)value$; break;
            case 5: latitude = (java.lang.Float)value$; break;
            case 6: longitude = (java.lang.Float)value$; break;
            case 7: phone = (java.lang.CharSequence)value$; break;
            case 8: taxCode = (java.lang.CharSequence)value$; break;
            case 9: businessCertificate = (java.lang.CharSequence)value$; break;
            case 10: applicationDate = (java.lang.CharSequence)value$; break;
            case 11: ownerName = (java.lang.CharSequence)value$; break;
            case 12: ownerAddress = (java.lang.CharSequence)value$; break;
            case 13: ownerCity = (java.lang.CharSequence)value$; break;
            case 14: ownerState = (java.lang.CharSequence)value$; break;
            case 15: ownerZip = (java.lang.CharSequence)value$; break;
            default: throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    /**
     * Gets the value of the 'businessId' field.
     */
    public java.lang.Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the value of the 'businessId' field.
     * @param value the value to set.
     */
    public void setBusinessId(java.lang.Integer value) {
        this.businessId = value;
    }

    /**
     * Gets the value of the 'name' field.
     */
    public java.lang.CharSequence getName() {
        return name;
    }

    /**
     * Sets the value of the 'name' field.
     * @param value the value to set.
     */
    public void setName(java.lang.CharSequence value) {
        this.name = value;
    }

    /**
     * Gets the value of the 'address' field.
     */
    public java.lang.CharSequence getAddress() {
        return address;
    }

    /**
     * Sets the value of the 'address' field.
     * @param value the value to set.
     */
    public void setAddress(java.lang.CharSequence value) {
        this.address = value;
    }

    /**
     * Gets the value of the 'city' field.
     */
    public java.lang.CharSequence getCity() {
        return city;
    }

    /**
     * Sets the value of the 'city' field.
     * @param value the value to set.
     */
    public void setCity(java.lang.CharSequence value) {
        this.city = value;
    }

    /**
     * Gets the value of the 'postalCode' field.
     */
    public java.lang.CharSequence getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the 'postalCode' field.
     * @param value the value to set.
     */
    public void setPostalCode(java.lang.CharSequence value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the 'latitude' field.
     */
    public java.lang.Float getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the 'latitude' field.
     * @param value the value to set.
     */
    public void setLatitude(java.lang.Float value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the 'longitude' field.
     */
    public java.lang.Float getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the 'longitude' field.
     * @param value the value to set.
     */
    public void setLongitude(java.lang.Float value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the 'phone' field.
     */
    public java.lang.CharSequence getPhone() {
        return phone;
    }

    /**
     * Sets the value of the 'phone' field.
     * @param value the value to set.
     */
    public void setPhone(java.lang.CharSequence value) {
        this.phone = value;
    }

    /**
     * Gets the value of the 'taxCode' field.
     */
    public java.lang.CharSequence getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the 'taxCode' field.
     * @param value the value to set.
     */
    public void setTaxCode(java.lang.CharSequence value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the 'businessCertificate' field.
     */
    public java.lang.CharSequence getBusinessCertificate() {
        return businessCertificate;
    }

    /**
     * Sets the value of the 'businessCertificate' field.
     * @param value the value to set.
     */
    public void setBusinessCertificate(java.lang.CharSequence value) {
        this.businessCertificate = value;
    }

    /**
     * Gets the value of the 'applicationDate' field.
     */
    public java.lang.CharSequence getApplicationDate() {
        return applicationDate;
    }

    /**
     * Sets the value of the 'applicationDate' field.
     * @param value the value to set.
     */
    public void setApplicationDate(java.lang.CharSequence value) {
        this.applicationDate = value;
    }

    /**
     * Gets the value of the 'ownerName' field.
     */
    public java.lang.CharSequence getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the value of the 'ownerName' field.
     * @param value the value to set.
     */
    public void setOwnerName(java.lang.CharSequence value) {
        this.ownerName = value;
    }

    /**
     * Gets the value of the 'ownerAddress' field.
     */
    public java.lang.CharSequence getOwnerAddress() {
        return ownerAddress;
    }

    /**
     * Sets the value of the 'ownerAddress' field.
     * @param value the value to set.
     */
    public void setOwnerAddress(java.lang.CharSequence value) {
        this.ownerAddress = value;
    }

    /**
     * Gets the value of the 'ownerCity' field.
     */
    public java.lang.CharSequence getOwnerCity() {
        return ownerCity;
    }

    /**
     * Sets the value of the 'ownerCity' field.
     * @param value the value to set.
     */
    public void setOwnerCity(java.lang.CharSequence value) {
        this.ownerCity = value;
    }

    /**
     * Gets the value of the 'ownerState' field.
     */
    public java.lang.CharSequence getOwnerState() {
        return ownerState;
    }

    /**
     * Sets the value of the 'ownerState' field.
     * @param value the value to set.
     */
    public void setOwnerState(java.lang.CharSequence value) {
        this.ownerState = value;
    }

    /**
     * Gets the value of the 'ownerZip' field.
     */
    public java.lang.CharSequence getOwnerZip() {
        return ownerZip;
    }

    /**
     * Sets the value of the 'ownerZip' field.
     * @param value the value to set.
     */
    public void setOwnerZip(java.lang.CharSequence value) {
        this.ownerZip = value;
    }

    /** Creates a new Business RecordBuilder */
    public static Business.Builder newBuilder() {
        return new Business.Builder();
    }

    /** Creates a new Business RecordBuilder by copying an existing Builder */
    public static Business.Builder newBuilder(Business.Builder other) {
        return new Business.Builder(other);
    }

    /** Creates a new Business RecordBuilder by copying an existing Business instance */
    public static Business.Builder newBuilder(Business other) {
        return new Business.Builder(other);
    }

    /**
     * RecordBuilder for Business instances.
     */
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Business>
            implements org.apache.avro.data.RecordBuilder<Business> {

        private int businessId;
        private java.lang.CharSequence name;
        private java.lang.CharSequence address;
        private java.lang.CharSequence city;
        private java.lang.CharSequence postalCode;
        private float latitude;
        private float longitude;
        private java.lang.CharSequence phone;
        private java.lang.CharSequence taxCode;
        private java.lang.CharSequence businessCertificate;
        private java.lang.CharSequence applicationDate;
        private java.lang.CharSequence ownerName;
        private java.lang.CharSequence ownerAddress;
        private java.lang.CharSequence ownerCity;
        private java.lang.CharSequence ownerState;
        private java.lang.CharSequence ownerZip;

        /** Creates a new Builder */
        private Builder() {
            super(Business.SCHEMA$);
        }

        /** Creates a Builder by copying an existing Builder */
        private Builder(Business.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.businessId)) {
                this.businessId = data().deepCopy(fields()[0].schema(), other.businessId);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.name)) {
                this.name = data().deepCopy(fields()[1].schema(), other.name);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.address)) {
                this.address = data().deepCopy(fields()[2].schema(), other.address);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.city)) {
                this.city = data().deepCopy(fields()[3].schema(), other.city);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.postalCode)) {
                this.postalCode = data().deepCopy(fields()[4].schema(), other.postalCode);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.latitude)) {
                this.latitude = data().deepCopy(fields()[5].schema(), other.latitude);
                fieldSetFlags()[5] = true;
            }
            if (isValidValue(fields()[6], other.longitude)) {
                this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
                fieldSetFlags()[6] = true;
            }
            if (isValidValue(fields()[7], other.phone)) {
                this.phone = data().deepCopy(fields()[7].schema(), other.phone);
                fieldSetFlags()[7] = true;
            }
            if (isValidValue(fields()[8], other.taxCode)) {
                this.taxCode = data().deepCopy(fields()[8].schema(), other.taxCode);
                fieldSetFlags()[8] = true;
            }
            if (isValidValue(fields()[9], other.businessCertificate)) {
                this.businessCertificate = data().deepCopy(fields()[9].schema(), other.businessCertificate);
                fieldSetFlags()[9] = true;
            }
            if (isValidValue(fields()[10], other.applicationDate)) {
                this.applicationDate = data().deepCopy(fields()[10].schema(), other.applicationDate);
                fieldSetFlags()[10] = true;
            }
            if (isValidValue(fields()[11], other.ownerName)) {
                this.ownerName = data().deepCopy(fields()[11].schema(), other.ownerName);
                fieldSetFlags()[11] = true;
            }
            if (isValidValue(fields()[12], other.ownerAddress)) {
                this.ownerAddress = data().deepCopy(fields()[12].schema(), other.ownerAddress);
                fieldSetFlags()[12] = true;
            }
            if (isValidValue(fields()[13], other.ownerCity)) {
                this.ownerCity = data().deepCopy(fields()[13].schema(), other.ownerCity);
                fieldSetFlags()[13] = true;
            }
            if (isValidValue(fields()[14], other.ownerState)) {
                this.ownerState = data().deepCopy(fields()[14].schema(), other.ownerState);
                fieldSetFlags()[14] = true;
            }
            if (isValidValue(fields()[15], other.ownerZip)) {
                this.ownerZip = data().deepCopy(fields()[15].schema(), other.ownerZip);
                fieldSetFlags()[15] = true;
            }
        }

        /** Creates a Builder by copying an existing Business instance */
        private Builder(Business other) {
            super(Business.SCHEMA$);
            if (isValidValue(fields()[0], other.businessId)) {
                this.businessId = data().deepCopy(fields()[0].schema(), other.businessId);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.name)) {
                this.name = data().deepCopy(fields()[1].schema(), other.name);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.address)) {
                this.address = data().deepCopy(fields()[2].schema(), other.address);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.city)) {
                this.city = data().deepCopy(fields()[3].schema(), other.city);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.postalCode)) {
                this.postalCode = data().deepCopy(fields()[4].schema(), other.postalCode);
                fieldSetFlags()[4] = true;
            }
            if (isValidValue(fields()[5], other.latitude)) {
                this.latitude = data().deepCopy(fields()[5].schema(), other.latitude);
                fieldSetFlags()[5] = true;
            }
            if (isValidValue(fields()[6], other.longitude)) {
                this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
                fieldSetFlags()[6] = true;
            }
            if (isValidValue(fields()[7], other.phone)) {
                this.phone = data().deepCopy(fields()[7].schema(), other.phone);
                fieldSetFlags()[7] = true;
            }
            if (isValidValue(fields()[8], other.taxCode)) {
                this.taxCode = data().deepCopy(fields()[8].schema(), other.taxCode);
                fieldSetFlags()[8] = true;
            }
            if (isValidValue(fields()[9], other.businessCertificate)) {
                this.businessCertificate = data().deepCopy(fields()[9].schema(), other.businessCertificate);
                fieldSetFlags()[9] = true;
            }
            if (isValidValue(fields()[10], other.applicationDate)) {
                this.applicationDate = data().deepCopy(fields()[10].schema(), other.applicationDate);
                fieldSetFlags()[10] = true;
            }
            if (isValidValue(fields()[11], other.ownerName)) {
                this.ownerName = data().deepCopy(fields()[11].schema(), other.ownerName);
                fieldSetFlags()[11] = true;
            }
            if (isValidValue(fields()[12], other.ownerAddress)) {
                this.ownerAddress = data().deepCopy(fields()[12].schema(), other.ownerAddress);
                fieldSetFlags()[12] = true;
            }
            if (isValidValue(fields()[13], other.ownerCity)) {
                this.ownerCity = data().deepCopy(fields()[13].schema(), other.ownerCity);
                fieldSetFlags()[13] = true;
            }
            if (isValidValue(fields()[14], other.ownerState)) {
                this.ownerState = data().deepCopy(fields()[14].schema(), other.ownerState);
                fieldSetFlags()[14] = true;
            }
            if (isValidValue(fields()[15], other.ownerZip)) {
                this.ownerZip = data().deepCopy(fields()[15].schema(), other.ownerZip);
                fieldSetFlags()[15] = true;
            }
        }

        /** Gets the value of the 'businessId' field */
        public java.lang.Integer getBusinessId() {
            return businessId;
        }

        /** Sets the value of the 'businessId' field */
        public Business.Builder setBusinessId(int value) {
            validate(fields()[0], value);
            this.businessId = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /** Checks whether the 'businessId' field has been set */
        public boolean hasBusinessId() {
            return fieldSetFlags()[0];
        }

        /** Clears the value of the 'businessId' field */
        public Business.Builder clearBusinessId() {
            fieldSetFlags()[0] = false;
            return this;
        }

        /** Gets the value of the 'name' field */
        public java.lang.CharSequence getName() {
            return name;
        }

        /** Sets the value of the 'name' field */
        public Business.Builder setName(java.lang.CharSequence value) {
            validate(fields()[1], value);
            this.name = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /** Checks whether the 'name' field has been set */
        public boolean hasName() {
            return fieldSetFlags()[1];
        }

        /** Clears the value of the 'name' field */
        public Business.Builder clearName() {
            name = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /** Gets the value of the 'address' field */
        public java.lang.CharSequence getAddress() {
            return address;
        }

        /** Sets the value of the 'address' field */
        public Business.Builder setAddress(java.lang.CharSequence value) {
            validate(fields()[2], value);
            this.address = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /** Checks whether the 'address' field has been set */
        public boolean hasAddress() {
            return fieldSetFlags()[2];
        }

        /** Clears the value of the 'address' field */
        public Business.Builder clearAddress() {
            address = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /** Gets the value of the 'city' field */
        public java.lang.CharSequence getCity() {
            return city;
        }

        /** Sets the value of the 'city' field */
        public Business.Builder setCity(java.lang.CharSequence value) {
            validate(fields()[3], value);
            this.city = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /** Checks whether the 'city' field has been set */
        public boolean hasCity() {
            return fieldSetFlags()[3];
        }

        /** Clears the value of the 'city' field */
        public Business.Builder clearCity() {
            city = null;
            fieldSetFlags()[3] = false;
            return this;
        }

        /** Gets the value of the 'postalCode' field */
        public java.lang.CharSequence getPostalCode() {
            return postalCode;
        }

        /** Sets the value of the 'postalCode' field */
        public Business.Builder setPostalCode(java.lang.CharSequence value) {
            validate(fields()[4], value);
            this.postalCode = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /** Checks whether the 'postalCode' field has been set */
        public boolean hasPostalCode() {
            return fieldSetFlags()[4];
        }

        /** Clears the value of the 'postalCode' field */
        public Business.Builder clearPostalCode() {
            postalCode = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        /** Gets the value of the 'latitude' field */
        public java.lang.Float getLatitude() {
            return latitude;
        }

        /** Sets the value of the 'latitude' field */
        public Business.Builder setLatitude(float value) {
            validate(fields()[5], value);
            this.latitude = value;
            fieldSetFlags()[5] = true;
            return this;
        }

        /** Checks whether the 'latitude' field has been set */
        public boolean hasLatitude() {
            return fieldSetFlags()[5];
        }

        /** Clears the value of the 'latitude' field */
        public Business.Builder clearLatitude() {
            fieldSetFlags()[5] = false;
            return this;
        }

        /** Gets the value of the 'longitude' field */
        public java.lang.Float getLongitude() {
            return longitude;
        }

        /** Sets the value of the 'longitude' field */
        public Business.Builder setLongitude(float value) {
            validate(fields()[6], value);
            this.longitude = value;
            fieldSetFlags()[6] = true;
            return this;
        }

        /** Checks whether the 'longitude' field has been set */
        public boolean hasLongitude() {
            return fieldSetFlags()[6];
        }

        /** Clears the value of the 'longitude' field */
        public Business.Builder clearLongitude() {
            fieldSetFlags()[6] = false;
            return this;
        }

        /** Gets the value of the 'phone' field */
        public java.lang.CharSequence getPhone() {
            return phone;
        }

        /** Sets the value of the 'phone' field */
        public Business.Builder setPhone(java.lang.CharSequence value) {
            validate(fields()[7], value);
            this.phone = value;
            fieldSetFlags()[7] = true;
            return this;
        }

        /** Checks whether the 'phone' field has been set */
        public boolean hasPhone() {
            return fieldSetFlags()[7];
        }

        /** Clears the value of the 'phone' field */
        public Business.Builder clearPhone() {
            phone = null;
            fieldSetFlags()[7] = false;
            return this;
        }

        /** Gets the value of the 'taxCode' field */
        public java.lang.CharSequence getTaxCode() {
            return taxCode;
        }

        /** Sets the value of the 'taxCode' field */
        public Business.Builder setTaxCode(java.lang.CharSequence value) {
            validate(fields()[8], value);
            this.taxCode = value;
            fieldSetFlags()[8] = true;
            return this;
        }

        /** Checks whether the 'taxCode' field has been set */
        public boolean hasTaxCode() {
            return fieldSetFlags()[8];
        }

        /** Clears the value of the 'taxCode' field */
        public Business.Builder clearTaxCode() {
            taxCode = null;
            fieldSetFlags()[8] = false;
            return this;
        }

        /** Gets the value of the 'businessCertificate' field */
        public java.lang.CharSequence getBusinessCertificate() {
            return businessCertificate;
        }

        /** Sets the value of the 'businessCertificate' field */
        public Business.Builder setBusinessCertificate(java.lang.CharSequence value) {
            validate(fields()[9], value);
            this.businessCertificate = value;
            fieldSetFlags()[9] = true;
            return this;
        }

        /** Checks whether the 'businessCertificate' field has been set */
        public boolean hasBusinessCertificate() {
            return fieldSetFlags()[9];
        }

        /** Clears the value of the 'businessCertificate' field */
        public Business.Builder clearBusinessCertificate() {
            businessCertificate = null;
            fieldSetFlags()[9] = false;
            return this;
        }

        /** Gets the value of the 'applicationDate' field */
        public java.lang.CharSequence getApplicationDate() {
            return applicationDate;
        }

        /** Sets the value of the 'applicationDate' field */
        public Business.Builder setApplicationDate(java.lang.CharSequence value) {
            validate(fields()[10], value);
            this.applicationDate = value;
            fieldSetFlags()[10] = true;
            return this;
        }

        /** Checks whether the 'applicationDate' field has been set */
        public boolean hasApplicationDate() {
            return fieldSetFlags()[10];
        }

        /** Clears the value of the 'applicationDate' field */
        public Business.Builder clearApplicationDate() {
            applicationDate = null;
            fieldSetFlags()[10] = false;
            return this;
        }

        /** Gets the value of the 'ownerName' field */
        public java.lang.CharSequence getOwnerName() {
            return ownerName;
        }

        /** Sets the value of the 'ownerName' field */
        public Business.Builder setOwnerName(java.lang.CharSequence value) {
            validate(fields()[11], value);
            this.ownerName = value;
            fieldSetFlags()[11] = true;
            return this;
        }

        /** Checks whether the 'ownerName' field has been set */
        public boolean hasOwnerName() {
            return fieldSetFlags()[11];
        }

        /** Clears the value of the 'ownerName' field */
        public Business.Builder clearOwnerName() {
            ownerName = null;
            fieldSetFlags()[11] = false;
            return this;
        }

        /** Gets the value of the 'ownerAddress' field */
        public java.lang.CharSequence getOwnerAddress() {
            return ownerAddress;
        }

        /** Sets the value of the 'ownerAddress' field */
        public Business.Builder setOwnerAddress(java.lang.CharSequence value) {
            validate(fields()[12], value);
            this.ownerAddress = value;
            fieldSetFlags()[12] = true;
            return this;
        }

        /** Checks whether the 'ownerAddress' field has been set */
        public boolean hasOwnerAddress() {
            return fieldSetFlags()[12];
        }

        /** Clears the value of the 'ownerAddress' field */
        public Business.Builder clearOwnerAddress() {
            ownerAddress = null;
            fieldSetFlags()[12] = false;
            return this;
        }

        /** Gets the value of the 'ownerCity' field */
        public java.lang.CharSequence getOwnerCity() {
            return ownerCity;
        }

        /** Sets the value of the 'ownerCity' field */
        public Business.Builder setOwnerCity(java.lang.CharSequence value) {
            validate(fields()[13], value);
            this.ownerCity = value;
            fieldSetFlags()[13] = true;
            return this;
        }

        /** Checks whether the 'ownerCity' field has been set */
        public boolean hasOwnerCity() {
            return fieldSetFlags()[13];
        }

        /** Clears the value of the 'ownerCity' field */
        public Business.Builder clearOwnerCity() {
            ownerCity = null;
            fieldSetFlags()[13] = false;
            return this;
        }

        /** Gets the value of the 'ownerState' field */
        public java.lang.CharSequence getOwnerState() {
            return ownerState;
        }

        /** Sets the value of the 'ownerState' field */
        public Business.Builder setOwnerState(java.lang.CharSequence value) {
            validate(fields()[14], value);
            this.ownerState = value;
            fieldSetFlags()[14] = true;
            return this;
        }

        /** Checks whether the 'ownerState' field has been set */
        public boolean hasOwnerState() {
            return fieldSetFlags()[14];
        }

        /** Clears the value of the 'ownerState' field */
        public Business.Builder clearOwnerState() {
            ownerState = null;
            fieldSetFlags()[14] = false;
            return this;
        }

        /** Gets the value of the 'ownerZip' field */
        public java.lang.CharSequence getOwnerZip() {
            return ownerZip;
        }

        /** Sets the value of the 'ownerZip' field */
        public Business.Builder setOwnerZip(java.lang.CharSequence value) {
            validate(fields()[15], value);
            this.ownerZip = value;
            fieldSetFlags()[15] = true;
            return this;
        }

        /** Checks whether the 'ownerZip' field has been set */
        public boolean hasOwnerZip() {
            return fieldSetFlags()[15];
        }

        /** Clears the value of the 'ownerZip' field */
        public Business.Builder clearOwnerZip() {
            ownerZip = null;
            fieldSetFlags()[15] = false;
            return this;
        }

        @Override
        public Business build() {
            try {
                Business record = new Business();
                record.businessId = fieldSetFlags()[0] ? this.businessId : (java.lang.Integer) defaultValue(fields()[0]);
                record.name = fieldSetFlags()[1] ? this.name : (java.lang.CharSequence) defaultValue(fields()[1]);
                record.address = fieldSetFlags()[2] ? this.address : (java.lang.CharSequence) defaultValue(fields()[2]);
                record.city = fieldSetFlags()[3] ? this.city : (java.lang.CharSequence) defaultValue(fields()[3]);
                record.postalCode = fieldSetFlags()[4] ? this.postalCode : (java.lang.CharSequence) defaultValue(fields()[4]);
                record.latitude = fieldSetFlags()[5] ? this.latitude : (java.lang.Float) defaultValue(fields()[5]);
                record.longitude = fieldSetFlags()[6] ? this.longitude : (java.lang.Float) defaultValue(fields()[6]);
                record.phone = fieldSetFlags()[7] ? this.phone : (java.lang.CharSequence) defaultValue(fields()[7]);
                record.taxCode = fieldSetFlags()[8] ? this.taxCode : (java.lang.CharSequence) defaultValue(fields()[8]);
                record.businessCertificate = fieldSetFlags()[9] ? this.businessCertificate : (java.lang.CharSequence) defaultValue(fields()[9]);
                record.applicationDate = fieldSetFlags()[10] ? this.applicationDate : (java.lang.CharSequence) defaultValue(fields()[10]);
                record.ownerName = fieldSetFlags()[11] ? this.ownerName : (java.lang.CharSequence) defaultValue(fields()[11]);
                record.ownerAddress = fieldSetFlags()[12] ? this.ownerAddress : (java.lang.CharSequence) defaultValue(fields()[12]);
                record.ownerCity = fieldSetFlags()[13] ? this.ownerCity : (java.lang.CharSequence) defaultValue(fields()[13]);
                record.ownerState = fieldSetFlags()[14] ? this.ownerState : (java.lang.CharSequence) defaultValue(fields()[14]);
                record.ownerZip = fieldSetFlags()[15] ? this.ownerZip : (java.lang.CharSequence) defaultValue(fields()[15]);
                return record;
            } catch (Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }
}