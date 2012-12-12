// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from category.proto

package nl.rootdev.android.kookjijclient2.datastructures.pb;

import java.io.IOException;

import nl.rootdev.android.kookjijclient2.datastructures.ICategory;

import com.dyuproject.protostuff.me.Input;
import com.dyuproject.protostuff.me.Message;
import com.dyuproject.protostuff.me.Output;
import com.dyuproject.protostuff.me.Schema;
import com.dyuproject.protostuff.me.UninitializedMessageException;

public final class Category implements Message, Schema, ICategory
{

    public static Schema getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Category getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Category DEFAULT_INSTANCE = new Category();

    
    private Integer setCurpage;
    private Integer setResultpages;
    private String items;

    public Category()
    {
        
    }

    public Category(
        Integer setCurpage,
        Integer setResultpages
    )
    {
        this.setCurpage = setCurpage;
        this.setResultpages = setResultpages;
    }

    // getters and setters

    // setCurpage

    public Integer getSetCurpage()
    {
        return setCurpage;
    }

    public void setSetCurpage(Integer setCurpage)
    {
        this.setCurpage = setCurpage;
    }

    // setResultpages

    public Integer getSetResultpages()
    {
        return setResultpages;
    }

    public void setSetResultpages(Integer setResultpages)
    {
        this.setResultpages = setResultpages;
    }

    // items

    public String getItems()
    {
        return items;
    }

    public void setItems(String items)
    {
        this.items = items;
    }

    // message method

    public Schema cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Object /*Category*/ newMessage()
    {
        return new Category();
    }

    public Class typeClass()
    {
        return Category.class;
    }

    public String messageName()
    {
        return "Category";
    }

    public String messageFullName()
    {
        return Category.class.getName();
    }

    public boolean isInitialized(Object /*Category*/ messageObj)
    {
        Category message = (Category)messageObj;
        return 
            message.setCurpage != null 
            && message.setResultpages != null;
    }

    public void mergeFrom(Input input, Object /*Category*/ messageObj) throws IOException
    {
        Category message = (Category)messageObj;
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.setCurpage = new Integer(input.readUInt32());
                    break;
                case 2:
                    message.setResultpages = new Integer(input.readUInt32());
                    break;
                case 3:
                    message.items = input.readString();
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }



    public void writeTo(Output output, Object /*Category*/ messageObj) throws IOException
    {
        Category message = (Category)messageObj;
        if(message.setCurpage == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(1, message.setCurpage.intValue(), false);

        if(message.setResultpages == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(2, message.setResultpages.intValue(), false);

        if(message.items != null)
            output.writeString(3, message.items, false);

    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "setCurpage";
            case 2: return "setResultpages";
            case 3: return "items";
            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = (Integer)__fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.Hashtable __fieldMap = new java.util.Hashtable();
    static
    {
        __fieldMap.put("setCurpage", new Integer(1));
        __fieldMap.put("setResultpages", new Integer(2));
        __fieldMap.put("items", new Integer(3));
    }
    
}
