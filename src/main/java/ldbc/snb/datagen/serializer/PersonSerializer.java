package ldbc.snb.datagen.serializer;

import ldbc.snb.datagen.dictionary.Dictionaries;
import ldbc.snb.datagen.objects.Knows;
import ldbc.snb.datagen.objects.Person;
import ldbc.snb.datagen.objects.StudyAt;
import ldbc.snb.datagen.objects.WorkAt;
import org.apache.hadoop.conf.Configuration;

import java.util.Iterator;

/**
 * Created by aprat on 10/15/14.
 */
abstract public class PersonSerializer {

    public PersonSerializer() {
	    
    }


    public void export(Person person) {

        serialize(person);

        long universityId = Dictionaries.universities.getUniversityFromLocation(person.universityLocationId());
        if (universityId != -1) {
            if (person.classYear() != -1) {
                StudyAt studyAt = new StudyAt();
                studyAt.year = person.classYear();
                studyAt.user = person.accountId();
                studyAt.university = universityId;
                serialize(studyAt);
            }
        }

        Iterator<Long> it = person.companies().keySet().iterator();
        while (it.hasNext()) {
            long companyId = it.next();
            WorkAt workAt = new WorkAt();
            workAt.company = companyId;
            workAt.user = person.accountId();
            workAt.year = person.companies().get(companyId);
            serialize(workAt);
        }
    }

    public void export(long personId, Knows k ) {
        if( personId < k.to().accountId())
            serialize(personId, k);
    }

    abstract public void initialize(Configuration conf, int reducerId);

    abstract public void close();

    abstract protected void serialize(Person p);

    abstract protected void serialize(StudyAt studyAt);

    abstract protected void serialize(WorkAt workAt);

    abstract protected void serialize(long personId, Knows knows);
}
