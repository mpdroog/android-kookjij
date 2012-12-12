// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from recipie.proto

package nl.rootdev.android.kookjijclient2.datastructures.pb;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import nl.rootdev.android.kookjijclient2.datastructures.IRecipie;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.UninitializedMessageException;

public final class Recipie implements Externalizable, Message<Recipie>, IRecipie
{

    public static Schema<Recipie> getSchema()
    {
        return SCHEMA;
    }

    public static Recipie getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Recipie DEFAULT_INSTANCE = new Recipie();

    
    // non-private fields
    // see http://developer.android.com/guide/practices/design/performance.html#package_inner
    Long id;
    String name;
    Integer preparationTime;
    String description;
    Long lastedit;
    Integer rating;
    String origin;
    Integer serves;
    String image;
    String ingredients;
    String comment;
    String introduction;

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
        Integer serves,
        String ingredients
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
        this.ingredients = ingredients;
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

    // ingredients

    public String getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    // comment

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    // introduction

    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
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

    public Schema<Recipie> cachedSchema()
    {
        return SCHEMA;
    }

    static final Schema<Recipie> SCHEMA = new Schema<Recipie>()
    {
        // schema methods

        public Recipie newMessage()
        {
            return new Recipie();
        }

        public Class<Recipie> typeClass()
        {
            return Recipie.class;
        }

        public String messageName()
        {
            return Recipie.class.getSimpleName();
        }

        public String messageFullName()
        {
            return Recipie.class.getName();
        }

        public boolean isInitialized(Recipie message)
        {
            return 
                message.id != null 
                && message.name != null 
                && message.preparationTime != null 
                && message.description != null 
                && message.lastedit != null 
                && message.rating != null 
                && message.origin != null 
                && message.serves != null 
                && message.ingredients != null;
        }

        public void mergeFrom(Input input, Recipie message) throws IOException
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
                        message.preparationTime = input.readUInt32();
                        break;
                    case 4:
                        message.description = input.readString();
                        break;
                    case 5:
                        message.lastedit = input.readUInt64();
                        break;
                    case 6:
                        message.rating = input.readUInt32();
                        break;
                    case 7:
                        message.origin = input.readString();
                        break;
                    case 8:
                        message.serves = input.readUInt32();
                        break;
                    case 9:
                        message.image = input.readString();
                        break;
                    case 10:
                        message.ingredients = input.readString();
                        break;
                    case 11:
                        message.comment = input.readString();
                        break;
                    case 12:
                        message.introduction = input.readString();
                        break;
                    default:
                        input.handleUnknownField(number, this);
                }   
            }
        }


        public void writeTo(Output output, Recipie message) throws IOException
        {
            if(message.id == null)
                throw new UninitializedMessageException(message);
            output.writeUInt64(1, message.id, false);

            if(message.name == null)
                throw new UninitializedMessageException(message);
            output.writeString(2, message.name, false);

            if(message.preparationTime == null)
                throw new UninitializedMessageException(message);
            output.writeUInt32(3, message.preparationTime, false);

            if(message.description == null)
                throw new UninitializedMessageException(message);
            output.writeString(4, message.description, false);

            if(message.lastedit == null)
                throw new UninitializedMessageException(message);
            output.writeUInt64(5, message.lastedit, false);

            if(message.rating == null)
                throw new UninitializedMessageException(message);
            output.writeUInt32(6, message.rating, false);

            if(message.origin == null)
                throw new UninitializedMessageException(message);
            output.writeString(7, message.origin, false);

            if(message.serves == null)
                throw new UninitializedMessageException(message);
            output.writeUInt32(8, message.serves, false);

            if(message.image != null)
                output.writeString(9, message.image, false);

            if(message.ingredients == null)
                throw new UninitializedMessageException(message);
            output.writeString(10, message.ingredients, false);

            if(message.comment != null)
                output.writeString(11, message.comment, false);

            if(message.introduction != null)
                output.writeString(12, message.introduction, false);
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
                case 10: return "ingredients";
                case 11: return "comment";
                case 12: return "introduction";
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
            fieldMap.put("preparationTime", 3);
            fieldMap.put("description", 4);
            fieldMap.put("lastedit", 5);
            fieldMap.put("rating", 6);
            fieldMap.put("origin", 7);
            fieldMap.put("serves", 8);
            fieldMap.put("image", 9);
            fieldMap.put("ingredients", 10);
            fieldMap.put("comment", 11);
            fieldMap.put("introduction", 12);
        }
    };
    
}
