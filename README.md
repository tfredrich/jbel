JBEL - Java Boolean Expression Language
=======================================

Originally, JBEL was built as a DSL to enhance the Java Collections API, implementing on-the-fly sorting & filtering capabilities over collections including, select(), collect(), reject(), detect(), doForEach() operations using functional programming techniques (e.g. Callbacks).

However, it is now also a general-purpose DSL to implement other logical DSLs that perform runtime expression evaluation. Need to create a DSL that gets evaluated at runtime? JBEL is your answer.

While there are other options (namely, OGNL, MVEL, JBoss EL, Spring EL, etc.), JBEL remains simple and easy to use.

JBEL is a small, efficient Java library enabling programmers to create simple, easily understandable expressions to perform collection filtering and/or sorting. It also supports the concept of closures to facilitate rapid implementation of filtering and sorting functionality. The following benefits are realized through consistent use of JBEL:

* No more copy-n-paste coding for comparing and filtering.
* Reduce duplicated code and code size.
* Improve filter and sort clarity, readability, and maintainability.
* Increase application reliability with reduced bug introduction.
* Utilize closure (see wikipedia) concepts in your Java code.

Collection Operations
=====================

The following examples should get you going on your understanding and justification for use of JBEL. After a look, the benefits are obvious, especially when considering the amount of copy-n-paste coding that often happens around comparing and filtering. For additional examples, check out the unit tests in the source code (available at SourceForge).

Filtering
---------

Filtering a collection is the act of selecting elements from the collection that meet certain criteria. This is accomplished via the select() operation.

Before JBEL

```java
private Collection filterCollection(Collection toFilter)
{
  Collection results = new Vector();
  Iterator iterator = toFilter.iterator();

  while (iterator.hasNext())
  {
    (SomeInstance) instance = (SomeInstance) iterator.next();

    if (instance.getAccountBalance() > 10000
        && instance.isNewCustomer())
    {
      results.add(instance);
    }
  }

  return results;
}

Collection results = filterCollection(toFilter);
```

After JBEL

```java
SelectExpressionBuilder builder = CollectionUtils.newSelectExpressionBuilder();
builder.attribute("accountBalance").greaterThan(10000)
  .and(builder.attribute("isNewCustomer").equalTo(true));

Collection results = CollectionUtils.select(toFilter, builder.getExpression());
```

Or... we could instead use closure-style functionality of JBEL:

```java
Collection results = CollectionUtils.select(toFilter, new AbstractPredicate()
  {
    public boolean test(Object argument)
    throws EvaluationException
    {
      Customer customer = (Customer) argument;
      return (customer.getAccountBalance() > 10000.0
        && customer.isNewCustomer());
    }
  }
);
```

Sorting/Collation
-----------------

Ordinarily, to accomplish ordering in a collection, you much implement Comparable or implement a Comparator.
With JBEL you can simply create a Comparator at runtime using a CollationExpressionBuilder.

Before JBEL

```java
public class PersonNameComparator
implements Comparator
{
  public int compare(Object obj1, Object obj2)
  {
    Person person1 = (Person) obj1;
    Person person2 = (Person) obj2;
    int result = person1.getLastName()
      .compareTo(person2.getLastName());

    if (result == 0)
    {
      result = person1.getFirstName()
        .compareTo(person2.getFirstName());
    }

    if (result == 0)
    {
      result = person1.getMiddleInitial()
        .compareTo(person2.getMiddleInitial());
    }

    return result;
  }
}

Collections.sort(peopleToSort, new PersonNameComparator());
```

After JBEL

```java
// Build a CollationExpression...
CollationExpressionBuilder orderBy = CollectionUtils.newCollationExpressionBuilder();
orderBy.attribute("lastName")
  .attribute("firstName")
  .attribute("middleInitial");

// Call Collections.sort() directly...
Collections.sort(peopleToSort, orderBy.getExpression());
// or... call Collections.sort() indirectly by simply evaluating the expression...
orderBy.evaluate(peopleToSort);
```
