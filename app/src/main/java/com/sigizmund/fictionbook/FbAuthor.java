package com.sigizmund.fictionbook;


import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class FbAuthor {
    abstract String firstName();
    @Nullable abstract String middleName();
    abstract String lastName();

    public static Builder builder() {
        return new AutoValue_FbAuthor.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setFirstName(String firstName);
        public abstract Builder setLastName(String lastName);
        public abstract Builder setMiddleName(String middleName);

        public abstract FbAuthor build();
    }
}
