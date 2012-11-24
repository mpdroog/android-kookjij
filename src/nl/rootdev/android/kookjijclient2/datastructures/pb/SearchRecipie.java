// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from search_recipie.proto

package nl.rootdev.android.kookjijclient2.datastructures.pb;

import java.io.IOException;
import java.util.Vector;

import nl.rootdev.android.kookjijclient2.datastructures.ISearchRecipie;

import com.dyuproject.protostuff.me.Input;
import com.dyuproject.protostuff.me.Message;
import com.dyuproject.protostuff.me.Output;
import com.dyuproject.protostuff.me.Schema;

public final class SearchRecipie implements Message, Schema, ISearchRecipie
{

    public static Schema getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static SearchRecipie getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final SearchRecipie DEFAULT_INSTANCE = new SearchRecipie();

    
    private Integer setResultpages;
    private Vector recipies;

    public SearchRecipie()
    {
        
    }

    // getters and setters

    // setResultpages

    public Integer getSetResultpages()
    {
        return setResultpages;
    }

    public void setSetResultpages(Integer setResultpages)
    {
        this.setResultpages = setResultpages;
    }

    // recipies

    public Vector getRecipiesList()
    {
        return recipies;
    }

    public void setRecipiesList(Vector recipies)
    {
        this.recipies = recipies;
    }

    // message method

    public Schema cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Object /*SearchRecipie*/ newMessage()
    {
        return new SearchRecipie();
    }

    public Class typeClass()
    {
        return SearchRecipie.class;
    }

    public String messageName()
    {
        return "SearchRecipie";
    }

    public String messageFullName()
    {
        return SearchRecipie.class.getName();
    }

    public boolean isInitialized(Object /*SearchRecipie*/ message)
    {
        return true;
    }

    public void mergeFrom(Input input, Object /*SearchRecipie*/ messageObj) throws IOException
    {
        SearchRecipie message = (SearchRecipie)messageObj;
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.setResultpages = new Integer(input.readUInt32());
                    break;
                case 2:
                    if(message.recipies == null)
                        message.recipies = new Vector();
                    message.recipies.addElement(input.mergeObject(null, Recipie.getSchema()));
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }



    public void writeTo(Output output, Object /*SearchRecipie*/ messageObj) throws IOException
    {
        SearchRecipie message = (SearchRecipie)messageObj;
        if(message.setResultpages != null)
            output.writeUInt32(1, message.setResultpages.intValue(), false);

        if(message.recipies != null)
        {
            for(int i = 0; i < message.recipies.size(); i++)
            {
                Recipie recipies = (Recipie)message.recipies.elementAt(i);
                if(recipies != null)
                    output.writeObject(2, recipies, Recipie.getSchema(), true);
            }
        }

    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "setResultpages";
            case 2: return "recipies";
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
        __fieldMap.put("setResultpages", new Integer(1));
        __fieldMap.put("recipies", new Integer(2));
    }
    
}
