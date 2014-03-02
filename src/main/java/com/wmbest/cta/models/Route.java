package com.wmbest.cta.models;

import android.content.ContentValues;
import android.provider.BaseColumns;

import org.simpleframework.xml.*;

import com.vokal.database.*;

@Root
public class Route extends AbstractDataModel implements BaseColumns {

    public static final String NAME       = "name";
    public static final String NUMBER     = "number";
    public static final String DIRECTION = "direction";

    @Element(required=false)
    public int id = -1;

    @Element(name="rt")
    public String number;

    @Element(name="rtnm")
    public String name;

    @Element(required=false)
    public String directions;

    public void populateContentValues(ContentValues aValues) {
        // put fields in aValues

        if (id != -1) aValues.put(_ID, id);
        aValues.put(NAME, name);
        aValues.put(NUMBER, number);
        aValues.put(DIRECTION, directions);

    }

    public static final SQLiteTable.TableCreator TABLE_CREATOR = new SQLiteTable.TableCreator() {

        @Override
        public SQLiteTable buildTableSchema(SQLiteTable.Builder aBuilder) {
            aBuilder.addIntegerColumn(_ID).primaryKey().autoincrement()
                .addStringColumn(NAME)
                .addStringColumn(NUMBER)
                .addStringColumn(DIRECTION)
                .unique(NAME, NUMBER);

            return aBuilder.build();
        }

        @Override
        public SQLiteTable updateTableSchema(SQLiteTable.Updater aUpdater, int aOldVersion) {
            return null;  // no upgrades yet...
        }
    };
}
