package ldbc.snb.datagen.serializer;

public enum FileName {

    // static
    TAG("Tag", 1.0),
    TAG_HASTYPE_TAGCLASS("Tag_hasType_TagClass", 1.0),
    TAGCLASS("TagClass", 1.0),
    TAGCLASS_ISSUBCLASSOF_TAGCLASS("TagClass_isSubclassOf_TagClass", 1.0),
    PLACE("Place", 1.0),
    PLACE_ISPARTOF_PLACE("Place_isPartOf_Place", 1.0),
    ORGANISATION("Organisation", 1.0),
    ORGANISATION_ISLOCATEDIN_PLACE("Organisation_isLocatedIn_Place", 1.0),

    // dynamic activity
    FORUM("Forum", 5.13),
    FORUM_HASMEMBER_PERSON("Forum_hasMember_Person", 384.06),
    FORUM_HASTAG_TAG("Forum_hasTag_Tag", 11.10),
    PERSON_LIKES_POST("Person_likes_Post", 141.12),
    PERSON_LIKES_COMMENT("Person_likes_Comment", 325.31),
    POST("Post", 138.61),
    POST_HASTAG_TAG("Post_hasTag_Tag", 77.34),
    COMMENT("Comment", 503.70),
    COMMENT_HASTAG_TAG("Comment_hasTag_Tag", 295.20),

    // dynamic person
    PERSON("Person", 1.0),
    PERSON_HASINTEREST_TAG("Person_hasInterest_Tag", 7.89),
    PERSON_WORKAT_COMPANY("Person_workAt_Company", 0.77),
    PERSON_STUDYAT_UNIVERSITY("Person_studyAt_University", 0.28),
    PERSON_KNOWS_PERSON("Person_knows_Person", 26.11),

    ;

    public final String name;
    public final double size;

    FileName(String name, double size) {
        this.name = name;
        this.size = size;
    }
}
