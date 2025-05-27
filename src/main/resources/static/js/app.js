/**
 * ToDoLister - Funcionalidades JavaScript
 */

/**
 * Alterna entre el tema claro y oscuro
 * @param {Event} event - El evento click
 */
function toggleTheme(event) {
  event.preventDefault();
  const body = document.body;
  const button = document.getElementById('darkThemeButton');
  const icon = button.querySelector('i');
  
  if (body.classList.contains('dark-theme')) {
    // Cambiar a tema claro
    body.classList.remove('dark-theme');
    icon.classList.remove('fa-sun');
    icon.classList.add('fa-moon');
    localStorage.setItem('theme', 'light');
  } else {
    // Cambiar a tema oscuro
    body.classList.add('dark-theme');
    icon.classList.remove('fa-moon');
    icon.classList.add('fa-sun');
    localStorage.setItem('theme', 'dark');
  }
}

/**
 * Filtra las tareas según su estado
 * @param {string} filter - El filtro a aplicar ('all', 'pending', 'completed')
 */
function filterTasks(filter) {
  const tasks = document.querySelectorAll('.task-item');
  
  tasks.forEach(task => {
    const isCompleted = task.getAttribute('data-completed') === 'true';
    
    switch(filter) {
      case 'all':
        task.style.display = 'block';
        break;
      case 'pending':
        task.style.display = isCompleted ? 'none' : 'block';
        break;
      case 'completed':
        task.style.display = isCompleted ? 'block' : 'none';
        break;
    }
  });
  
  // Actualizar botones de filtro
  document.querySelectorAll('.filter-btn').forEach(btn => {
    if (btn.getAttribute('data-filter') === filter) {
      btn.classList.add('active');
    } else {
      btn.classList.remove('active');
    }
  });
  
  // Guardar preferencia de filtro
  localStorage.setItem('taskFilter', filter);
}

/**
 * Inicializa la aplicación cuando el DOM está listo
 */
document.addEventListener('DOMContentLoaded', () => {
  // Aplicar tema guardado
  const savedTheme = localStorage.getItem('theme');
  const button = document.getElementById('darkThemeButton');
  const icon = button.querySelector('i');
  
  if (savedTheme === 'dark') {
    document.body.classList.add('dark-theme');
    icon.classList.remove('fa-moon');
    icon.classList.add('fa-sun');
  }
  
  // Configurar filtros de tareas
  const filterButtons = document.querySelectorAll('.filter-btn');
  if (filterButtons.length > 0) {
    filterButtons.forEach(btn => {
      btn.addEventListener('click', () => {
        const filter = btn.getAttribute('data-filter');
        filterTasks(filter);
      });
    });
    
    // Aplicar filtro guardado o por defecto
    const savedFilter = localStorage.getItem('taskFilter') || 'all';
    filterTasks(savedFilter);
  }
});
