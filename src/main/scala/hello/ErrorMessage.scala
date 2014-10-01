package hello
import java.util.Collections
import java.util.Arrays
import scala.beans.BeanProperty
import java.util.List

class ErrorMessage() {

  @BeanProperty
  var errors: List[String] = _

  def this(errors: List[String]) {
    this()
    this.errors = errors
  }

  def this(error: String) {
    this(Collections.singletonList(error))
  }

  def this(errors: String*) {
    this(Arrays.asList(errors:_*))
  }

}