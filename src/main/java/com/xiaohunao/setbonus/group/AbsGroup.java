package com.xiaohunao.setbonus.group;

import com.google.gson.JsonObject;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.data.ItemVerifier;

public class AbsGroup implements IGroup {
    public ItemVerifier verifier;

    public static class Builder {
        private ItemVerifier verifier;

        public Builder setVerifier(ItemVerifier verifier) {
            this.verifier = verifier;
            return this;
        }

        public AbsGroup build() {
            AbsGroup group = new AbsGroup();
            group.verifier = verifier;
            return group;
        }
    }

    @Override
    public IGroup read(JsonObject jsonObject) {
       return new Builder().setVerifier(ItemVerifier.read(jsonObject.get("verifier").getAsJsonObject())).build();
    }

}
