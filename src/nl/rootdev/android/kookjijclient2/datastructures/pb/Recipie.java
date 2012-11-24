package nl.rootdev.android.kookjijclient2.datastructures.pb;

import java.io.IOException;

import nl.rootdev.android.kookjijclient2.datastructures.IRecipie;

import com.dyuproject.protostuff.me.Input;
import com.dyuproject.protostuff.me.Message;
import com.dyuproject.protostuff.me.Output;
import com.dyuproject.protostuff.me.Schema;
import com.dyuproject.protostuff.me.UninitializedMessageException;

public final class Recipie implements Message, Schema, IRecipie
{

    public static Schema getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Recipie getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Recipie DEFAULT_INSTANCE = new Recipie();

    
    private Long id;
    private String name;
    private Integer preparationTime;
    private String description;
    private Long lastedit;
    private Integer rating;
    private String origin;
    private Integer serves;
    private String image;

    public Recipie()
    {
        
    }

    public Recipie(
        Long id,
        String name,
        Integer preparationTime,
        String description,
        Long lastedit,
        Integer rating,
        String origin,
        Integer serves
    )
    {
        this.id = id;
        this.name = name;
        this.preparationTime = preparationTime;
        this.description = description;
        this.lastedit = lastedit;
        this.rating = rating;
        this.origin = origin;
        this.serves = serves;
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

    // preparationTime

    public Integer getPreparationTime()
    {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime)
    {
        this.preparationTime = preparationTime;
    }

    // description

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    // rating

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    // origin

    public String getOrigin()
    {
        return origin;
    }

    public void setOrigin(String origin)
    {
        this.origin = origin;
    }

    // serves

    public Integer getServes()
    {
        return serves;
    }

    public void setServes(Integer serves)
    {
        this.serves = serves;
    }

    // image

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    // message method

    public Schema cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Object /*Recipie*/ newMessage()
    {
        return new Recipie();
    }

    public Class typeClass()
    {
        return Recipie.class;
    }

    public String messageName()
    {
        return "Recipie";
    }

    public String messageFullName()
    {
        return Recipie.class.getName();
    }

    public boolean isInitialized(Object /*Recipie*/ messageObj)
    {
        Recipie message = (Recipie)messageObj;
        return 
            message.id != null 
            && message.name != null 
            && message.preparationTime != null 
            && message.description != null 
            && message.lastedit != null 
            && message.rating != null 
            && message.origin != null 
            && message.serves != null;
    }

    public void mergeFrom(Input input, Object /*Recipie*/ messageObj) throws IOException
    {
        Recipie message = (Recipie)messageObj;
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.id = new Long(input.readUInt64());
                    break;
                case 2:
                    message.name = input.readString();
                    break;

                case 3:
                    message.preparationTime = new Integer(input.readUInt32());
                    break;
                case 4:
                    message.description = input.readString();
                    break;

                case 5:
                    message.lastedit = new Long(input.readUInt64());
                    break;
                case 6:
                    message.rating = new Integer(input.readUInt32());
                    break;
                case 7:
                    message.origin = input.readString();
                    break;

                case 8:
                    message.serves = new Integer(input.readUInt32());
                    break;
                case 9:
                    message.image = input.readString();
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }



    public void writeTo(Output output, Object /*Recipie*/ messageObj) throws IOException
    {
        Recipie message = (Recipie)messageObj;
        if(message.id == null)
            throw new UninitializedMessageException(message);
        output.writeUInt64(1, message.id.longValue(), false);

        if(message.name == null)
            throw new UninitializedMessageException(message);
        output.writeString(2, message.name, false);


        if(message.preparationTime == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(3, message.preparationTime.intValue(), false);

        if(message.description == null)
            throw new UninitializedMessageException(message);
        output.writeString(4, message.description, false);


        if(message.lastedit == null)
            throw new UninitializedMessageException(message);
        output.writeUInt64(5, message.lastedit.longValue(), false);

        if(message.rating == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(6, message.rating.intValue(), false);

        if(message.origin == null)
            throw new UninitializedMessageException(message);
        output.writeString(7, message.origin, false);


        if(message.serves == null)
            throw new UninitializedMessageException(message);
        output.writeUInt32(8, message.serves.intValue(), false);

        if(message.image != null)
            output.writeString(9, message.image, false);

    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "id";
            case 2: return "name";
            case 3: return "preparationTime";
            case 4: return "description";
            case 5: return "lastedit";
            case 6: return "rating";
            case 7: return "origin";
            case 8: return "serves";
            case 9: return "image";
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
        __fieldMap.put("id", new Integer(1));
        __fieldMap.put("name", new Integer(2));
        __fieldMap.put("preparationTime", new Integer(3));
        __fieldMap.put("description", new Integer(4));
        __fieldMap.put("lastedit", new Integer(5));
        __fieldMap.put("rating", new Integer(6));
        __fieldMap.put("origin", new Integer(7));
        __fieldMap.put("serves", new Integer(8));
        __fieldMap.put("image", new Integer(9));
    }
    
}
