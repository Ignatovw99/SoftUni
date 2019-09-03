import utils.HibernatePersistData;
import utils.HibernateRetrieveDataByCriteria;
import utils.HibernateRetrieveDataByQuery;
import utils.HibernateUtils;

public class Main {
    public static void main(String[] args) {
        HibernateUtils.setupHibernate();
        HibernatePersistData.execute();
        HibernateRetrieveDataByQuery.execute();
        HibernateRetrieveDataByCriteria.execute();
    }
}
