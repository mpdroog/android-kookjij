// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from category_item.proto

package nl.rootdev.android.kookjijclient2.datastructures.pb;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import nl.rootdev.android.kookjijclient2.datastructures.ICategoryItem;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.UninitializedMessageException;

public final class CategoryItem implements Externalizable, Message<CategoryItem>, ICategoryItem
{

    public static Schema<CategoryItem> getSchema()
    {
        return SCHEMA;
    }

    public static CategoryItem getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final CategoryItem DEFAULT_INSTANCE = new CategoryItem();

    
    // non-private fields
    // see http://developer.android.com/guide/practices/design/performance.html#package_inner
    Long id;
    String name;
    Integer rating;
    Long lastedit;

    public CategoryItem()
    {
        
    }

    public CategoryItem(
        Long id,
        String name,
        Long lastedit
    )
    {
        this.id = id;
        this.name = name;
        this.lastedit = lastedit;
    }

    // getters and setters

    // id

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    // name

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    // rating

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    // lastedit

    public Long getLastedit()
    {
        return lastedit;
    }

    public void setLastedit(Long lastedit)
    {
        this.lastedit = lastedit;
    }

    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, SCHEMA);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, SCHEMA);
    }

    // message method

    public Schema<CategoryItem> cachedSchema()
    {
        return SCHEMA;
    }

    static final Schema<CategoryItem> SCHEMA = new Schema<CategoryItem>()
    {
        // schema methods

        public CategoryItem newMessage()
        {
            return new CategoryItem();
        }

        public Class<CategoryItem> typeClass()
        {
            return CategoryItem.class;
        }

        public String messageName()
        {
            return CategoryItem.class.getSimpleName();
        }

        public String messageFullName()
        {
            return CategoryItem.class.getName();
        }

        public boolean isInitialized(CategoryItem message)
        {
            return 
                message.id != null 
                && message.name != null 
                && message.lastedit != null;
        }

        public void mergeFrom(Input input, CategoryItem message) throws IOException
        {
            for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
            {
                switch(number)
                {
                    case 0:
                        return;
                    case 1:
                        message.id = input.readUInt64();
                        break;
                    case 2:
                        message.name = input.readString();
                        break;
                    case 3:
                        message.rating = input.readUInt32();
                        break;
                    case 4:
                        message.lastedit = input.readUInt64();
                        break;
                    default:
                        input.handleUnknownField(number, this);
                }   
            }
        }


        public void writeTo(Output output, CategoryItem message) throws IOException
        {
            if(message.id == null)
                throw new UninitializedMessageException(message);
            output.writeUInt64(1, message.id, false);

            if(message.name == null)
                throw new UninitializedMessageException(message);
            output.writeString(2, message.name, false);

            if(message.rating != null)
                output.writeUInt32(3, message.rating, false);

            if(message.lastedit == null)
                throw new UninitializedMessageException(message);
            output.writeUInt64(4, message.lastedit, false);
        }

        public String getFieldName(int number)
        {
            switch(number)
            {
                case 1: return "id";
                case 2: return "name";
                case 3: return "rating";
                case 4: return "lastedit";
                default: return null;
            }
        }

        public int getFieldNumber(String name)
        {
            final Integer number = fieldMap.get(name);
            return number == null ? 0 : number.intValue();
        }

        final java.util.HashMap<String,Integer> fieldMap = new java.util.HashMap<String,Integer>();
        {
            fieldMap.put("id", 1);
            fieldMap.put("name", 2);
            fieldMap.put("rating", 3);
            fieldMap.put("lastedit", 4);
        }
    };
    
}
