// Protótipo funcional: interações mínimas armazenadas no localStorage
document.addEventListener('DOMContentLoaded', ()=>{
  const clientesKey = 'petcare_clientes_v1';
  const produtosKey = 'petcare_produtos_v1';
  let clientes = JSON.parse(localStorage.getItem(clientesKey) || '[]');
  let produtos = JSON.parse(localStorage.getItem(produtosKey) || '[]');

  const clientesList = document.getElementById('clientes-list');
  const produtosList = document.getElementById('produtos-list');
  const agendaList = document.getElementById('agenda-list');

  function renderClientes(){
    clientesList.innerHTML = '';
    if(clientes.length===0){
      clientesList.innerHTML = '<div class="card small"><p class="muted">Nenhum cliente cadastrado.</p></div>';
      return;
    }
    clientes.forEach(c => {
      const div = document.createElement('div');
      div.className = 'card small';
      div.innerHTML = `<h4>${c.nome}</h4><p class="muted">CPF: ${c.cpf} • ${c.telefone || ''}</p>
        <p class="muted small">Pets: ${ (c.pets||[]).map(p=>p.nome).join(', ') || '-' }</p>
        <div style="margin-top:.5rem"><button class="btn-ghost" data-cpf="${c.cpf}">Ver</button> <button class="btn-primary" data-cpf="${c.cpf}" data-action="add-pet">Adicionar pet</button></div>`;
      clientesList.appendChild(div);
    });
  }

  function renderProdutos(){
    produtosList.innerHTML = '';
    if(produtos.length===0){
      produtosList.innerHTML = '<div class="card small"><p class="muted">Nenhum produto cadastrado.</p></div>';
      return;
    }
    produtos.forEach(p => {
      const div = document.createElement('div');
      div.className = 'card small';
      div.innerHTML = `<h4>${p.nome}</h4><p class="muted">${p.categoria} • R$ ${p.preco.toFixed(2)}</p><p class="muted">Estoque: ${p.estoque}</p>`;
      produtosList.appendChild(div);
    });
  }

  // quick register client
  document.getElementById('quick-register').addEventListener('submit', e=>{
    e.preventDefault();
    const nome = document.getElementById('nome').value.trim();
    const cpf = document.getElementById('cpf').value.trim();
    const telefone = document.getElementById('telefone').value.trim();
    if(!nome||!cpf){ alert('Preencha nome e CPF'); return; }
    clientes.push({nome, cpf, telefone, pets:[]});
    localStorage.setItem(clientesKey, JSON.stringify(clientes));
    document.getElementById('quick-register').reset();
    renderClientes();
  });

  // add demo product if none
  if(produtos.length===0){
    produtos.push({nome:'Ração Premium', categoria:'Ração', preco:120.0, estoque:10});
    produtos.push({nome:'Coleira Confort', categoria:'Acessório', preco:25.0, estoque:20});
    localStorage.setItem(produtosKey, JSON.stringify(produtos));
  }

  // agendamento submit
  document.getElementById('agendamento-form').addEventListener('submit', e=>{
    e.preventDefault();
    const pet = document.getElementById('ag-pet').value.trim();
    const serv = document.getElementById('ag-servico').value;
    const data = document.getElementById('ag-data').value;
    const pro = document.getElementById('ag-pro').value.trim();
    if(!pet||!data){ alert('Preencha pet e data'); return; }
    const ags = JSON.parse(localStorage.getItem('petcare_agenda_v1')||'[]');
    ags.push({pet, serv, data, pro});
    localStorage.setItem('petcare_agenda_v1', JSON.stringify(ags));
    renderAgenda();
    document.getElementById('agendamento-form').reset();
  });

  function renderAgenda(){
    const ags = JSON.parse(localStorage.getItem('petcare_agenda_v1')||'[]');
    agendaList.innerHTML = '';
    if(ags.length===0){ agendaList.innerHTML = '<li class="muted">Nenhum agendamento</li>'; return; }
    ags.slice(0,8).forEach(a=>{
      const li = document.createElement('li');
      li.innerHTML = `<strong>${a.pet}</strong> — ${a.serv} • <span class="muted">${a.data} • ${a.pro || '—'}</span>`;
      agendaList.appendChild(li);
    });
  }

  // initial render
  renderClientes();
  renderProdutos();
  renderAgenda();
});
